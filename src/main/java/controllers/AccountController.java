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
 * @author Artem Khomich 
 * @author Viktoriya Malinova
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
	private Pane accountContainer;
	@FXML
	private Pane accountDetail;
	@FXML
	private GridPane accountList;
	/*
	 * @FXML private FlowPane accountAddPane;
	 * 
	 * @FXML private Button AddAccountButton;
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		accountList.setVisible(true);
		accountAdd.setVisible(false);
		accountDetail.setVisible(false);
		accountListController.AccountAddClick(new ToAccountAddHandler());
		accountAddController.setOnBackButtonClick(new ToMainHandler());
		
		accountListController.setupAccounts(new ToAccountDetailHandler());	//sets eventHandler to ech account
		accountDetailController.returnToMain(new ToMainHandler());
	}

/*	@FXML
	public void switchAccountView() {
		accountList.setVisible(!accountList.isVisible());
		accountAdd.setVisible(!accountAdd.isVisible());
	}*/
	
	//viktos- handles clicking on the listed accounts -> see AccountDetailController
		public class ToAccountDetailHandler implements EventHandler<MouseEvent>{
			@Override
			public void handle(MouseEvent event){
				SingleAccountView accountView= (SingleAccountView)event.getSource();
				Account t = accountView.getAccount();
				try{
				accountDetailController.setAccount(t);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				 //see accountDetail.fxml for setup of  accountDetailView
				accountList.setVisible(false);
				accountAdd.setVisible(false);
				accountDetail.setVisible(true);			
			}
		}

/*	@FXML
	public void AccountAddClick() {
		switchAccountView();
	}

	public class ViewChangeClickHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			switchAccountView();
		}

	}*/
	
	
	public class ToMainHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			accountList.setVisible(true);
			accountAdd.setVisible(false);
			accountDetail.setVisible(false);
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
