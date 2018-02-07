package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
	private TransactionListController transactionListController;
	@FXML
	private TransactionDetailController transactionDetailController;
	@FXML
	private AccountController accountController;
	@FXML
	private ScrollPane transactionList;
	@FXML
	private Pane transactionDetail;
	@FXML
	private Pane accountView;

	@FXML
	private BorderPane mainView;

	@FXML
	private StackPane detailPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		mainView.setDividerPosition(0, 0.3);
		transactionList.setVisible(false);
		accountView.setVisible(true);
		transactionDetail.setVisible(false);
		transactionListController.setOnTransactionViewClick(new TransactionViewClickHandler());
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

	@FXML
	public void handleTransactionListClick() {
		accountView.setVisible(false);
		transactionList.setVisible(true);
		transactionDetail.setVisible(false);
	}

	@FXML
	public void handleAccountListClick() {
		accountView.setVisible(true);
		transactionList.setVisible(false);
		transactionDetail.setVisible(false);	}

	@FXML
	public void handleSettingsClick() {
		// TODO: Implement action
	}

	@FXML
	public void handleAddTransactionClick() {
		// TODO: Implement action
	}

}
