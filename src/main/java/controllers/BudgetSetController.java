package main.java.controllers;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.dao.AccountDao;
import main.java.models.Account;
import main.java.models.Budget;
import javafx.scene.Node;

public class BudgetSetController implements Initializable{

	//view
	@FXML
	private Pane BudgetSet;
	@FXML
	private TextField BudgetGoal;
	@FXML 
	private TextField SetDateBy;
	@FXML 
	private TextField SetSalary;
	
	private AccountDetailController accountDetailController;
	private AccountDao dao;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub	
		dao = new AccountDao();
	}
	
	public void setAccountDetailController(AccountDetailController account) {
		this.accountDetailController = account;
	}
	
	public void updateBudget(ActionEvent event) {
		boolean input_failed = verifyProperInput();

		Budget budget = new Budget();
		Account account = accountDetailController.getAccount();
		if(input_failed) {System.out.println("err");}
		else {
			budget.setFields(BudgetGoal.getText(), SetDateBy.getText(), SetSalary.getText());
			double weekly_allowance = budget.calculateWeeklySpendingLimit(account.getBalance());
			if(weekly_allowance == -1) {
				input_failed = true;
				BudgetGoal.setText("Goal Impossible - " + BudgetGoal.getText());
				SetDateBy.setText("Goal Impossible - " + SetDateBy.getText());		
				SetSalary.setText("Goal Impossible - " + SetSalary.getText());	
			}
			else {
				account.setBudgetProperties(budget);
				confirmAndSendBudget(budget);
				account.setBudgetProperties(budget);
				dao.updateAccount(account);
			}
		}
	}
	
	public boolean verifyProperInput() {
		boolean input_failed = false;
		if(testProperField("^\\d+\\.\\d{2}$", BudgetGoal.getText()));
		else {
			BudgetGoal.setText("Format Error - " + BudgetGoal.getText());
			input_failed = true;
		}
		//date poor format
		if(testProperField("^2\\d{3}\\/(0{0,1}[0-9]|1[0-2]{0,1})\\/([0-2]{0,1}[0-9]|3[0-1])$", SetDateBy.getText())) {
			String[] date_components = SetDateBy.getText().split("\\/");
			int day_goal =  Integer.parseInt(date_components[2]);
			int month_goal = Integer.parseInt(date_components[1]);
			/*https://stackoverflow.com/questions/5799140/java-get-month-string-from-integer*/
			String month_string = new DateFormatSymbols().getMonths()[month_goal-1];
			int year_goal = Integer.parseInt(date_components[0]);
			int days_in_month = (new GregorianCalendar(year_goal, month_goal - 1, 1)).getActualMaximum(Calendar.DAY_OF_MONTH);
			//days not in month
			if(days_in_month < day_goal) {
				SetDateBy.setText(month_string + " Days Error - " + SetDateBy.getText()); 
				input_failed = true;
			}
			//date before or current
			else {
				int current_year = Calendar.getInstance().get(Calendar.YEAR);
				int current_month =  Calendar.getInstance().get(Calendar.MONTH) + 1;
				int current_day =  Calendar.getInstance().get(Calendar.DATE);
				if(current_year > year_goal ||
						(current_year == year_goal && current_month > month_goal) ||
						(current_year == year_goal && current_month == month_goal && current_day > day_goal)) {
					SetDateBy.setText("Date Passed - " + SetDateBy.getText());		
					input_failed = true;
				}
				else {
				}
			}
		}
		else {
			SetDateBy.setText("Date Error - " + SetDateBy.getText());
		 	input_failed = true;
		}
		
		if(testProperField("^\\d+\\.\\d{2}$", SetSalary.getText()));
		else {
			SetSalary.setText("Format Error - " + SetSalary.getText());
			input_failed = true;
		}
		
		return input_failed;
	}
	
	public void confirmAndSendBudget(Budget budget) {
		BudgetSet.setVisible(false);
		accountDetailController.setDetailValues(budget);
		accountDetailController.setDetailsVissible();
		accountDetailController.flipGoalButtonText();
	}
	
	public void backToDetails(ActionEvent event) {
		BudgetSet.setVisible(false);
	}
	
	public boolean testProperField(String regex, String comparision) {
		Pattern propper_input_regex = Pattern.compile(regex);
		Matcher match_goal_text =  propper_input_regex.matcher(comparision);
		return match_goal_text.find();		
	}
	
	
}
