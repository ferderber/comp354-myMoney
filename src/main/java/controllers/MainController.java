package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import main.java.models.Account;
import main.java.models.Transaction;
import main.java.views.TransactionView;

/**
 * Controls the MainView of the application. Responsible for handling the left
 * menu and transitions between different views.
 * 
 * @author Matthew Ferderber
 *
 */
public class MainController implements Initializable {
	@FXML
	private TransactionListControllerTable transactionListController;
	@FXML
	private TransactionDetailController transactionDetailController;
	@FXML
	private AccountInfoController accountInfoController;
	@FXML
	private AccountController accountController;
	@FXML
	private AnchorPane transactionList;
	@FXML
	private Pane transactionDetail;
	@FXML
	private StackPane accInfoPane;
	@FXML
	private Pane accountView;
	@FXML
	private Pane transactionAdd;

	private Account currentAccount;
	@FXML
	private BorderPane mainView;

	@FXML
	private StackPane detailPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setAllViewsInvisible();
		accountView.setVisible(true);
		transactionListController.setHandler(new TransactionRowClickHandler());
		//transactionListController.setOnTransactionViewClick(new TransactionViewClickHandler());
	}

	/**
	 * Switches between detail views
	 */
	private void switchDetailView() {
		transactionList.setVisible(!transactionList.isVisible());
		transactionDetail.setVisible(!transactionDetail.isVisible());
	}

	/**
	 * Handles clicking on Transactions. Switches to the corresponding detail view.
	 */
	public class TransactionViewClickHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			TransactionView transactionView = (TransactionView) event.getSource();
			Transaction t = transactionView.getTransaction();
			transactionDetailController.setTransaction(t);
			switchDetailView();
		}

	}
	public int getCurrentAccountId() {
		return currentAccount.getId();
	}
	public class TransactionRowClickHandler implements EventHandler<MouseEvent> {

		@SuppressWarnings("unchecked")
		@Override
		public void handle(MouseEvent event) {
			TableRow<Transaction> row = (TableRow<Transaction>) event.getSource();//Row ends up being null.
			Transaction t = row.getItem();
			transactionDetailController.setTransaction(t);//DetailController is getting null.
			switchDetailView();
		
		}

	}
	@FXML
	public void handleTransactionListClick() {
		//transactionListController.createTransactionList();
		setAllViewsInvisible();
		transactionList.setVisible(true);
	}

	@FXML
	public void handleAccountListClick() {
		setAllViewsInvisible();
		accountView.setVisible(true);
	}

	@FXML
	public void handleSettingsClick() {
		// TODO: Implement action
	}

	@FXML
	public void handleAddTransactionClick() {
		setAllViewsInvisible();
		transactionAdd.setVisible(true);
	}

	/**
	 * Makes all views Invisible (useful for changing between views)
	 */
	public void setAllViewsInvisible() {
		accInfoPane.setVisible(false);
		transactionAdd.setVisible(false);
		accountView.setVisible(false);
		transactionList.setVisible(false);
		transactionDetail.setVisible(false);
	}

}
