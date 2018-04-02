package test.account.budget;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.MyMoneyDriver;

public class BudgetTest extends ApplicationTest{
	
	public void start(Stage stage) throws Exception {
		new MyMoneyDriver().start(stage);
	}
	
	//Interface shows up for no set budget
	@Test
	public void testAccountClickedNoData() {
		clickOn("View Accounts");
		assertTrue(lookup("#accountListContainer").query().isVisible());
		Pane account_list_pane = (Pane) lookup("#accountListContainer").query();
		assertTrue(account_list_pane.getChildren().size() > 0);
		clickOn(account_list_pane.getChildren().get(0));
		
		//Test user display no data
		assertTrue(!lookup("#BudgetProgress").query().isVisible());
		assertTrue(!lookup("#WeeklySpending").query().isVisible());
		assertTrue(!lookup("#Salary").query().isVisible());
		assertTrue(lookup("#SetGoal").query().isVisible());
	}

	//Budget fields show
	@Test
	public void testBudgetFields() {
		clickOn("View Accounts");
		assertTrue(lookup("#accountListContainer").query().isVisible());
		Pane account_list_pane = (Pane) lookup("#accountListContainer").query();
		assertTrue(account_list_pane.getChildren().size() > 0);
		clickOn(account_list_pane.getChildren().get(0));
		assertTrue(!lookup("#BudgetProgress").query().isVisible());
		assertTrue(!lookup("#WeeklySpending").query().isVisible());
		assertTrue(!lookup("#Salary").query().isVisible());
		assertTrue(lookup("#SetGoal").query().isVisible());
		clickOn("Set Goal");	
		
		//Test that inputs set up
		assertTrue(lookup("#BudgetGoal").query().isVisible());
		assertTrue(lookup("#SetDateBy").query().isVisible());
		assertTrue(lookup("#SetSalary").query().isVisible());
		assertTrue(lookup("#ConfirmGoal").query().isVisible());	
	}
	
	//A goal being set does the right actions
	@Test
	public void testBudgetInputNoErr() {
		//check interface created, no data interface
		clickOn("View Accounts");
		assertTrue(lookup("#accountListContainer").query().isVisible());
		Pane account_list_pane = (Pane) lookup("#accountListContainer").query();
		assertTrue(account_list_pane.getChildren().size() > 0);
		clickOn(account_list_pane.getChildren().get(0));
		
		//Test that inputs set up
		clickOn("#BudgetGoal");
		write("200000");
		clickOn("#SetDateBy");
		write("2019/02/04");
		clickOn("#SetSalary");
		write("2000");
		clickOn("#ConfirmGoal");	
		
		//Test user display with data
		assertTrue(lookup("#BudgetProgress").query().isVisible());
		assertTrue(lookup("#WeeklySpending").query().isVisible());
		assertTrue(lookup("#Salary").query().isVisible());
		assertTrue(lookup("#SetGoal").query().isVisible());
	}
	
	//Date is before current
	@Test
	public void testBudgetInputEarlyDateErr() {
		//check interface created, no data interface
		clickOn("View Accounts");
		assertTrue(lookup("#accountListContainer").query().isVisible());
		Pane account_list_pane = (Pane) lookup("#accountListContainer").query();
		assertTrue(account_list_pane.getChildren().size() > 0);
		clickOn(account_list_pane.getChildren().get(0));
		
		//Test that inputs set up
		clickOn("#BudgetGoal");
		write("200000");
		clickOn("#SetDateBy");
		write("2010/02/04");
		clickOn("#SetSalary");
		write("2000");
		clickOn("#ConfirmGoal");	
	}
	
	//Goal is not in good format
		@Test
	public void testBudgetInputInvalidGoalErr() {
			//check interface created, no data interface
			clickOn("View Accounts");
			assertTrue(lookup("#accountListContainer").query().isVisible());
			Pane account_list_pane = (Pane) lookup("#accountListContainer").query();
			assertTrue(account_list_pane.getChildren().size() > 0);
			clickOn(account_list_pane.getChildren().get(0));
			
			//Test that inputs set up
			clickOn("#BudgetGoal");
			write("..200000");
			clickOn("#SetDateBy");
			write("2010/02/04");
			clickOn("#SetSalary");
			write("2000");
			clickOn("#ConfirmGoal");	
		}
		
	//Date is not in good format
	@Test
	public void testBudgetInputInvalidDateErr() {
		//check interface created, no data interface
		clickOn("View Accounts");
		assertTrue(lookup("#accountListContainer").query().isVisible());
		Pane account_list_pane = (Pane) lookup("#accountListContainer").query();
		assertTrue(account_list_pane.getChildren().size() > 0);
		clickOn(account_list_pane.getChildren().get(0));
		
		//Test that inputs set up
		clickOn("#BudgetGoal");
		write("200000");
		clickOn("#SetDateBy");
		write("2010/02/04");
		clickOn("#SetSalary");
		write("2000");
		clickOn("#ConfirmGoal");	
	}
	
	//Is impossible to achieve goal by date
	@Test
	public void testBudgetInputImpossibleDateErr() {
		//check interface created, no data interface
		clickOn("View Accounts");
		assertTrue(lookup("#accountListContainer").query().isVisible());
		Pane account_list_pane = (Pane) lookup("#accountListContainer").query();
		assertTrue(account_list_pane.getChildren().size() > 0);
		clickOn(account_list_pane.getChildren().get(0));
		
		//Test that inputs set up
		clickOn("#BudgetGoal");
		write("200000");
		clickOn("#SetDateBy");
		write("2010/02/04");
		clickOn("#SetSalary");
		write("2000");
		clickOn("#ConfirmGoal");	
	}
	
	//Salary format is wrong
	@Test
	public void testBudgetInputInvalidSalaryErr() {
		//check interface created, no data interface
		clickOn("View Accounts");
		assertTrue(lookup("#accountListContainer").query().isVisible());
		Pane account_list_pane = (Pane) lookup("#accountListContainer").query();
		assertTrue(account_list_pane.getChildren().size() > 0);
		clickOn(account_list_pane.getChildren().get(0));
		
		//Test that inputs set up
		clickOn("#BudgetGoal");
		write("200000");
		clickOn("#SetDateBy");
		write("2010/02/04");
		clickOn("#SetSalary");
		write(".2000");
		clickOn("#ConfirmGoal");	
	}

	//Proper items show up on entry
	@Test
	public void testAccountClickedWithData() {
		//check interface created, has data interface showing progress bar
		assertTrue(lookup("#accountListContainer").query().isVisible());
		Pane account_list_pane = (Pane) lookup("#accountListContainer").query();
		assertTrue(account_list_pane.getChildren().size() > 0);
		clickOn(account_list_pane.getChildren().get(0));
		//Test user display with data
		assertTrue(lookup("#BudgetProgress").query().isVisible());
		assertTrue(lookup("#WeeklySpending").query().isVisible());
		assertTrue(lookup("#Salary").query().isVisible());
		assertTrue(lookup("#SetGoal").query().isVisible());
		
		//test values
	}	
	
	//Modify entries
	@Test
	public void testModifyData() {
		//check interface created, has data interface showing progress bar
		assertTrue(lookup("#accountListContainer").query().isVisible());
		Pane account_list_pane = (Pane) lookup("#accountListContainer").query();
		assertTrue(account_list_pane.getChildren().size() > 0);
		clickOn(account_list_pane.getChildren().get(0));
		//Test user display with data
		assertTrue(lookup("#BudgetProgress").query().isVisible());
		assertTrue(lookup("#WeeklySpending").query().isVisible());
		assertTrue(lookup("#Salary").query().isVisible());
		assertTrue(lookup("#SetGoal").query().isVisible());
		clickOn("Set Goal");
		//Test that inputs set up
		assertTrue(lookup("#SetBalance").query().isVisible());
		assertTrue(lookup("#SetDateBy").query().isVisible());
		assertTrue(lookup("#SetSalary").query().isVisible());
		assertTrue(lookup("#ConfirmGoal").query().isVisible());
	}
}
