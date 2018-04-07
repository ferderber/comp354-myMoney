package test.budget;

import static org.junit.Assert.assertTrue;


import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.service.query.PointQuery;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.java.MyMoneyDriver;
import main.java.models.Budget;

public class BudgetTest extends ApplicationTest{
	
	public void start(Stage stage) throws Exception {
		new MyMoneyDriver().start(stage);
	}
	
	
	public void setupAccount() {
		clickOn("View Accounts");
		clickOn("Add Account");
		clickOn("#nameField");
		write("A B");
		clickOn("#numberField");
		write("123");
		clickOn("#typeField");
		write("1");
		clickOn("#balanceField");
		write("123");
		clickOn("Save Account");
		clickOn("Back");
	}
	
	public void enterDetails() {
		//fails without point2d
		Bounds bound = (lookup("#accountListContainer").query()).localToScreen(lookup("#accountListContainer").query().getBoundsInLocal());
		clickOn(new Point2D(bound.getMinX(), bound.getMinY() + 30));
	}
	
	//Interface shows up for no set budget
	//Clear database before running test for positive
	@Test
	public void testAccountClickedNoData() {
		setupAccount();
		enterDetails();
		//Test user display no data
		assertTrue(!lookup("#GoalProgressBar").query().isVisible());
		assertTrue(!lookup("#ProgToGoalText").query().isVisible());
		assertTrue(!lookup("#WeeklyBudgetText").query().isVisible());
		assertTrue(lookup("#GoalSetButton").query().isVisible());
	}

	//Budget fields show
	@Test
	public void testBudgetFields() {
		enterDetails();
		clickOn("#GoalSetButton");	
		
		//Test that inputs set up
		assertTrue(lookup("#BudgetGoal").query().isVisible());
		assertTrue(lookup("#SetDateBy").query().isVisible());
		assertTrue(lookup("#SetSalary").query().isVisible());
		assertTrue(lookup("#ConfirmGoal").query().isVisible());	
	}
	
	//Goal is not in good format
	@Test
	public void testBudgetInputInvalidBalanceErr() {
		enterDetails();
			clickOn("#GoalSetButton");	
			
			//Test that inputs set up
			clickOn("#BudgetGoal");
			write("..200000.999");
			clickOn("#ConfirmGoal");	

			assertTrue(((TextInputControl) lookup("#BudgetGoal").query()).getText().equals("Format Error - ..200000.999"));
		}
		
	//Date is not in good format - Regex detect
	@Test
	public void testBudgetInputInvalidDateErrFormatFail() {
		//check interface created, no data interface
		enterDetails();
		clickOn("#GoalSetButton");	
		
		//Test that date set up on conditions that meet the regex search
		clickOn("#SetDateBy");
		write("2100/20/1");
		clickOn("#ConfirmGoal");		
		assertTrue(((TextInputControl) lookup("#SetDateBy").query()).getText().equals("Date Error - 2100/20/1"));
	
		((TextInputControl) lookup("#SetDateBy").query()).setText("");
		
		clickOn("#SetDateBy");
		write("2100/12/32");
		clickOn("#ConfirmGoal");		
		assertTrue(((TextInputControl) lookup("#SetDateBy").query()).getText().equals("Date Error - 2100/12/32"));
	
	}
	
	//Date is not in good format - Month Conformity Check
	@Test
	public void testBudgetInputInvalidDateErrMonthDayFail() {
		//check interface created, no data interface
		enterDetails();
		clickOn("#GoalSetButton");	
		
		//Test that date set up on conditions that don't meet the regex search
		clickOn("#SetDateBy");
		write("2100/02/31");
		clickOn("#ConfirmGoal");	
		
		assertTrue(((TextInputControl) lookup("#SetDateBy").query()).getText().equals("February Days Error - 2100/02/31"));

		((TextInputControl) lookup("#SetDateBy").query()).setText("");
		
		//Test that date set up on conditions that don't meet the regex search
		clickOn("#SetDateBy");
		write("2100/4/31");
		clickOn("#ConfirmGoal");	
		
		assertTrue(((TextInputControl) lookup("#SetDateBy").query()).getText().equals("April Days Error - 2100/4/31"));		
		
		((TextInputControl) lookup("#SetDateBy").query()).setText("");
		
		//Test that date set up on conditions that meet the regex search
		clickOn("#SetDateBy");
		write("2100/4/28");
		clickOn("#ConfirmGoal");	
		
		assertTrue(((TextInputControl) lookup("#SetDateBy").query()).getText().equals("2100/4/28"));		
	
	}
	//Date is before current
	@Test
	public void testBudgetInputInvalidDateErrEarlyDateFail() {
		//check interface created, no data interface
		enterDetails();
		clickOn("#GoalSetButton");	
		
		//Test that date set up on early dates
		clickOn("#SetDateBy");
		write("2009/4/30");
		clickOn("#ConfirmGoal");	
		
		assertTrue(((TextInputControl) lookup("#SetDateBy").query()).getText().equals("Date Passed - 2009/4/30"));			
	}
		
	//Salary format is wrong
	@Test
	public void testBudgetInputInvalidSalaryErr() {
		enterDetails();
		clickOn("#GoalSetButton");	
		
		//Test that inputs set up
		clickOn("#SetSalary");
		write("0.999");
		clickOn("#ConfirmGoal");	

		assertTrue(((TextInputControl) lookup("#SetSalary").query()).getText().equals("Format Error - 0.999"));
	
	}
	
	@Test
	//test model day function
	//Use https://www.timeanddate.com/date/duration.html to get positives
	public void testDayCounter() {
		// As of 03/04/2018
		Budget test_budget_time1 = new Budget("","2018/4/26","");
		assertTrue(test_budget_time1.convertDateToDays() == 22);
		Budget test_budget_time2 = new Budget("","2018/5/26","");
		assertTrue(test_budget_time2.convertDateToDays() == 52);
		Budget test_budget_time3 = new Budget("","2018/6/26","");
		assertTrue(test_budget_time3.convertDateToDays() == 83);
		Budget test_budget_time4 = new Budget("","2019/6/26","");
		assertTrue(test_budget_time4.convertDateToDays() == 448);
		Budget test_budget_time5 = new Budget("","2019/3/26","");
		assertTrue(test_budget_time5.convertDateToDays() == 356 );
		Budget test_budget_time6 = new Budget("","2021/3/26","");
		assertTrue(test_budget_time6.convertDateToDays() == 1087);
	}
	
	@Test
	//Test weekly calculations
	public void testWeeklyAllowance() {
		Budget test_budget_time1 = new Budget("200000.00","2018/4/26","20.00");
		assertTrue(test_budget_time1.calculateWeeklySpendingLimit(123.00) == -1);
		
		// 22 days 39.86363636363637$ per day (279.0454545454546 / wk)
		Budget test_budget_time2 = new Budget("1000.00","2018/4/26","20000.00");
		assertTrue(test_budget_time2.calculateWeeklySpendingLimit(123.00) != -1);
		
		// 22 days 10.454545454545455$ per day (73.18181818181815 / wk)
		Budget test_budget_time3 = new Budget("1000.00","2018/4/26","20000.00");
		assertTrue(test_budget_time3.calculateWeeklySpendingLimit(1230.00) != -1);
	}
	
	//Is impossible to achieve goal by date
	@Test
	public void testBudgetInputImpossibleGoalErr() {
		enterDetails();
		clickOn("#GoalSetButton");	
				
		//Test that inputs set up
		clickOn("#BudgetGoal");
		write("200000.00");
		clickOn("#SetDateBy");
		write("2019/02/04");
		clickOn("#SetSalary");
		write("20.00");
		clickOn("#ConfirmGoal");
		
		assertTrue(((TextInputControl) lookup("#BudgetGoal").query()).getText().equals("Goal Impossible - " + "200000.00"));
		assertTrue(((TextInputControl) lookup("#SetDateBy").query()).getText().equals("Goal Impossible - " + "2019/02/04"));
		assertTrue(((TextInputControl) lookup("#SetSalary").query()).getText().equals("Goal Impossible - " + "20.00"));
	}
		
	//A goal being set does the right actions
	@Test
	public void testBudgetInputNoErr() {
		enterDetails();
		clickOn("#GoalSetButton");	
		
		//Test that inputs set up
		clickOn("#BudgetGoal");
		write("1000.00");
		clickOn("#SetDateBy");
		write("2019/02/28");
		clickOn("#SetSalary");
		write("20000.00");
		clickOn("#ConfirmGoal");	
		
		//send back to main screen and check visibility
		assertTrue(lookup("#GoalProgressBar").query().isVisible());
		assertTrue(lookup("#ProgToGoalText").query().isVisible());
		assertTrue(lookup("#WeeklyBudgetText").query().isVisible());
		assertTrue(lookup("#GoalSetButton").query().isVisible());	
	}


	//Proper items show up on entry. implies data stored
	@Test
	public void testAccountClickedWithData() {
		enterDetails();
		//Test user display with data
		assertTrue(lookup("#GoalProgressBar").query().isVisible());
		assertTrue(lookup("#ProgToGoalText").query().isVisible());
		assertTrue(lookup("#WeeklyBudgetText").query().isVisible());
		assertTrue(lookup("#GoalSetButton").query().isVisible());	
		//test values
	}	
	
	//Modify entries
	@Test
	public void testModifyData() {
		enterDetails();
		//check visibility of a;ready #GoalSetButton
		assertTrue(lookup("#GoalProgressBar").query().isVisible());
		assertTrue(lookup("#ProgToGoalText").query().isVisible());
		assertTrue(lookup("#WeeklyBudgetText").query().isVisible());
		assertTrue(lookup("#GoalSetButton").query().isVisible());	
		
		clickOn("#GoalSetButton");	
		
		//Test that inputs set up
		clickOn("#BudgetGoal");
		write("1000.00");
		clickOn("#SetDateBy");
		write("2019/02/28");
		clickOn("#SetSalary");
		write("20000.00");
		clickOn("#ConfirmGoal");			
	}
}
