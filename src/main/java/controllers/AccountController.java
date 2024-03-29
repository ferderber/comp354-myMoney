package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.controllers.AccountAddController;
import main.java.controllers.AccountListController;
import main.java.models.Account;
import main.java.views.SingleAccountView;;

/**
 * Provides an Account Controller. Switches between Account-add pane, account-list pane 
 * and account-detail pane
 * 
 * @author Viktoriya Malinova
 * @author Artem Khomich 
 *
 *
 */
public class AccountController implements Initializable {

	@FXML
	private Pane accountAdd;
	@FXML
	private AccountAddController accountAddController;
	@FXML
	private AccountListController accountListController;
	@FXML
	private AccountDetailController accountDetailController;
	@FXML
	private BudgetSetController budgetSetController;
	@FXML
	private Pane accountContainer;
	@FXML
	private Pane accountDetail;
	@FXML
	private Pane budgetSet;
	@FXML
	private GridPane accountList;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateAccounts();	
	}

	@FXML //sets eventHandler to each account
	public void updateAccounts() {
		accountList.setVisible(true);
		accountAdd.setVisible(false);
		accountDetail.setVisible(false);
		budgetSet.setVisible(false);
		accountListController.AccountAddClick(new ToAccountAddHandler());
		accountListController.setupAccounts(new ToAccountDetailHandler());
		accountAddController.setOnBackButtonClick(new ToMainHandler());
		accountDetailController.returnToMain(new ToMainHandler());
		accountDetailController.switchToBudgetView(new ToBudgetSetHandler());
	}

	//handles clicking on the listed accounts -> see AccountDetailController
	public class ToAccountDetailHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event){
			updateAccounts();
			SingleAccountView accountView= (SingleAccountView)event.getSource();
			Account t = accountView.getAccount();
			try{
				accountDetailController.setAccount(t);
				budgetSetController.setAccountDetailController(accountDetailController);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			//see accountDetail.fxml for setup of  accountDetailView
			accountList.setVisible(false);
			accountAdd.setVisible(false);
			budgetSet.setVisible(false);
			accountDetail.setVisible(true);			
		}
	}

	public class ToMainHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			accountList.setVisible(true);
			accountAdd.setVisible(false);
			accountDetail.setVisible(false);
			updateAccounts();	
		}

	}
	
	public class ToBudgetSetHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			budgetSet.setVisible(true);
			//updateAccounts();	
		}

	}

	public class ToAccountAddHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			accountList.setVisible(false);
			accountAdd.setVisible(true);
			accountDetail.setVisible(false);
		}

	}
}
