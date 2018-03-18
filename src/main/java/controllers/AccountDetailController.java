package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.dao.TransactionDao;
import main.java.models.Account;
import main.java.models.Transaction;
import main.java.views.TransactionView;


/**
 * Displays a detailed view of a given Account & associated transactions
 * 
 * @author Viktoriya Malinova
 *
 */

public class AccountDetailController  implements Initializable {


	@FXML
	private GridPane accountDetailView;
	@FXML
	private ObjectProperty<Account> accountProperty = new SimpleObjectProperty<>();
	@FXML
	private Button returnToMainViewButton  = new Button("Return");
	@FXML
	private VBox container=new VBox();
	@FXML
	private ScrollPane scroll = new ScrollPane();
	List<TransactionView> transactionViews;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	}
	
	//sets everything
	public void setAccount(Account acc) {
		this.accountProperty.set(acc);
		this.setVBox();
		this.getTransactions();
		this.setScrollPane();
	}
	
	//gets transactions from database & adds them as views in transactionViews
	public void getTransactions(){
		
		//initializing database and lists
		TransactionDao dao = new TransactionDao();
		List<Transaction> transactionsList = new ArrayList<>();
		transactionViews= new ArrayList<TransactionView>();
		//making sure both are empty
		transactionViews.clear();
		container.getChildren().clear();
		transactionsList=dao.getAllTransactionsById(getAccount().getId());	//get 
		transactionsList.forEach((transaction) -> {
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
		accountDetailView.add(new Text("Last updated: "),0,6);
		//if no changes, prints N/A rather than null
		if (getAccount().getEdit()==null)
			accountDetailView.add(new Text ("N/A"), 1, 6);
		else
			accountDetailView.add(new Text (getAccount().getEdit()+""), 1, 6);
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
	
	//controlls return button
	public void returnToMain(EventHandler<MouseEvent> handler) {
		returnToMainViewButton.setOnMouseClicked(handler);	
	}


}
