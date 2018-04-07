package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JProgressBar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.dao.AccountDao;
import main.java.dao.TransactionDao;
import main.java.models.Account;
import main.java.models.Budget;
import main.java.models.Transaction;
import main.java.views.TransactionView;


/**
 * Displays a detailed view of a given Account & associated transactions
 * 
 * @author Viktoriya Malinova
 * @author Kai Nicoll-Griffith
 */

public class AccountDetailController  implements Initializable {


	@FXML
	private GridPane accountDetailView;
	@FXML
	private ObjectProperty<Account> accountProperty = new SimpleObjectProperty<>();
	@FXML
	private Button returnToMainViewButton  = new Button("Return");
	@FXML 
	private Button deleteAccountButton = new Button("Delete Account");
	@FXML
	private Button set_goal_button = new Button();
	@FXML
	private VBox container=new VBox();
	@FXML
	private ScrollPane scroll = new ScrollPane();
	private List<TransactionView> transactionViews;

	@FXML
	private ProgressBar progress_bar_goal;
	@FXML
	private ProgressIndicator progress_indicator_to_goal;
	@FXML
	private Text date_by_text;
	@FXML
	private Text salary_text;
	@FXML
	private Text end_goal_text_obj;
	@FXML
	private Text weekly_budjet_text_obj;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	}

	//sets everything
	public void setAccount(Account acc) {
		this.accountProperty.set(acc);
		this.setVBox();
		this.setTransactions();
		this.setScrollPane();
	}

	//gets transactions from database & adds them as views in transactionViews
	public List<Transaction> getTransactions(){

		//initializing database and lists
		TransactionDao dao = new TransactionDao();
		List<Transaction> transactionsList = new ArrayList<>();
		transactionViews= new ArrayList<TransactionView>();
		//making sure both are empty
		transactionViews.clear();
		transactionsList=dao.getAllTransactionsById(getAccount().getId());	//get 
		return transactionsList;
	}

	//adds transactions into container and sets container
	public void setTransactions(){
		List<Transaction> transactionList= getTransactions();
		container.getChildren().clear();
		transactionList.forEach((transaction) -> {
			transactionViews.add(new TransactionView(transaction, null)); //to be changed if necessary
		});
		container.setPrefHeight(100);
		container.getChildren().addAll(transactionViews);
	}

	public void setScrollPane(){
		scroll.setContent(null);
		scroll.prefHeight(50.0);
		scroll.getStyleClass().add("accDetailScroll");
		scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroll.setContent(container);
		accountDetailView.add(scroll, 1, 7);
	}

	public void setVBox(){
		accountDetailView.getChildren().clear();
		accountDetailView.add(new Text("Name: "),0,0);
		accountDetailView.add(new Text (getAccount().getName()), 1, 0);
		accountDetailView.add(new Text("Number: "),0,1);
		accountDetailView.add(new Text(getAccount().getNumber()+""), 1, 1);
		accountDetailView.add(new Text("Type: "),0,2);
		accountDetailView.add(new Text (getAccount().getTypeName()), 1, 2);
		accountDetailView.add(new Text("Balance: "),0,3);
		accountDetailView.add(new Text (getAccount().getBalance()+""), 1, 3);
		accountDetailView.add(new Text("ID: "),0,4);
		accountDetailView.add(new Text (getAccount().getId()+""), 1, 4);
		accountDetailView.add(new Text("Date of creation: "),0,5);
		accountDetailView.add(new Text (getAccount().getCreate()+""), 1, 5);
		accountDetailView.add(returnToMainViewButton, 0, 7);
		deleteAccountButton.setOnAction(e->deleteAccount());
		accountDetailView.add(deleteAccountButton, 0, 8);
		accountDetailView.add(new Text("Last updated: "),0,6);
		//if no changes, prints N/A rather than null
		if (getAccount().getEdit()==null)
			accountDetailView.add(new Text ("N/A"), 1, 6);
		else
			accountDetailView.add(new Text (getAccount().getEdit()+""), 1, 6);
		
		//Goal Setting - KNG
		setBudgetDetails();
	}

	
	public void setBudgetDetails() {
		//Goal Setting - KNG
		accountDetailView.add(new Text(""),0, 8);
		accountDetailView.add(new Text("Progress: "), 0, 9);
		
		progress_bar_goal = new ProgressBar(0.6);
		progress_bar_goal.setId("GoalProgressBar");
		progress_bar_goal.setMaxWidth(Double.MAX_VALUE);
		accountDetailView.add(progress_bar_goal , 0, 10);
		
		progress_indicator_to_goal = new ProgressIndicator(0.6);
		progress_indicator_to_goal.setId("GoalProgressIndicator");
		accountDetailView.add(progress_indicator_to_goal, 0, 10);
		
		accountDetailView.add(new Text(""),0, 11);
		String date_deadline = "1992/10/02";
		date_by_text = new Text("Goal Deadline: " + date_deadline);
		date_by_text.setId("ProgToGoalText");
		accountDetailView.add(date_by_text, 0, 13);
		
		String end_goal = "$100";
		end_goal_text_obj = new Text("End Goal[End]: " + end_goal);
		end_goal_text_obj.setId("EndGoalText");
		accountDetailView.add(end_goal_text_obj, 0, 14);
		
		String salary = "$100";
		salary_text = new Text("Salary: " + salary);
		salary_text.setId("Salary");
		accountDetailView.add(salary_text, 0, 15);
		
		String weekly_budget = "$10.0";
		weekly_budjet_text_obj = new Text("Weekly Budget[Salary - Savings]: " + weekly_budget);
		weekly_budjet_text_obj.setId("WeeklyBudgetText");
		accountDetailView.add(weekly_budjet_text_obj,0,16);

		accountDetailView.add(new Text(""),0, 17);
//		Button set_goal_button = new Button("");
		set_goal_button.setId("GoalSetButton");
		set_goal_button.setMaxWidth(Double.MAX_VALUE);
		accountDetailView.add(set_goal_button, 0, 18);
		
		if(this.getAccount().getBudgetProperties().getDateBalance() != null) {
			setDetailValues(getAccount().getBudgetProperties());
			progress_bar_goal.setVisible(true);
			progress_indicator_to_goal.setVisible(true);
			date_by_text.setVisible(true);
			end_goal_text_obj.setVisible(true);
			salary_text.setVisible(true);
			weekly_budjet_text_obj.setVisible(true);
			set_goal_button.setVisible(true);
			set_goal_button.setText("Modify Goal");
		}
		else { 
			progress_bar_goal.setVisible(false);
			progress_indicator_to_goal.setVisible(false);
			date_by_text.setVisible(false);
			end_goal_text_obj.setVisible(false);
			weekly_budjet_text_obj.setVisible(false);
			salary_text.setVisible(false);
			set_goal_button.setVisible(true);
			set_goal_button.setText("Set Goal");
		}
	}
	
	public void setDetailsVissible() {
		ObservableList<Node> nodes = accountDetailView.getChildren();
		int all_nodes = nodes.size();
		for(int node = 0 ; node < all_nodes; node++) {
			nodes.get(node).setVisible(true);
		}
	}
	
	public void setDetailValues(Budget budget) {
		double progress_percent = this.getAccount().getBalance() / Double.parseDouble(budget.getGoalBalance());
		progress_bar_goal.setProgress(progress_percent);
		progress_indicator_to_goal.setProgress(progress_percent);
		//curr_prog_text_obj.set;
		end_goal_text_obj.setText("End Goal[End]: " + budget.getGoalBalance());
		date_by_text.setText("Goal Deadline: " + budget.getDateBalance());
		salary_text.setText("Yearly Salary: " +  budget.getSalary());
		String weekly= "" + budget.calculateWeeklySpendingLimit(this.getAccount().getBalance());
		weekly = weekly.substring(0, weekly.indexOf(".") + 3);
		weekly_budjet_text_obj.setText("Weekly Budget[Salary - Savings]: " + weekly);
	}
	
	
	public void flipGoalButtonText() {
		set_goal_button.setText("Modify Goal");
	}

	public String name(){
		return getAccount().getName();
	}

	public ObjectProperty<Account> accountProperty() {
		return accountProperty;
	}

	public Account getAccount() {
		return accountProperty.get();
	}
	
	//set goals
	public void switchToBudgetView(EventHandler<MouseEvent> handler) {
		set_goal_button.setOnMouseClicked(handler);
	}

	//controls return button
	public void returnToMain(EventHandler<MouseEvent> handler) {
		returnToMainViewButton.setOnMouseClicked(handler);	
	}
	
	
	@FXML
	private void deleteAccount() {
		Account a = accountProperty.get();
		if (a != null) {
			List<Transaction> transactionsList = getTransactions();
			AccountDao accDao = new AccountDao();
			TransactionDao tDao=new TransactionDao();
			for (Transaction t: transactionsList){
				t.delete();
				tDao.delete(t);
			}
			accDao.delete(a);
			
		}
	}

}
