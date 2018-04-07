package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
	private TransactionAddController transactionAddController;
	@FXML
	private AccountController accountViewController;
	@FXML
	private GridPane transactionList;
	@FXML
	private Pane transactionDetail;
	@FXML
	private Pane accountView;
	@FXML
	private Pane transactionAdd;
	@FXML
	private Pane statisticsView;
	@FXML
	private BorderPane mainView;

	@FXML
	private StackPane detailPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setAllViewsInvisible();
		accountView.setVisible(true);
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
		transactionListController.createTransactionList();
		setAllViewsInvisible();
		transactionList.setVisible(true);
	}

	@FXML
	public void handleAccountListClick() {
<<<<<<< HEAD
	/*	try{
		accountController.updateAccounts();
		}
		catch(Exception e){
			e.printStackTrace();
		}*/
=======
		accountViewController.updateAccounts();
>>>>>>> b252ccb6a613baec7a66f3aecefc5db8702d8d71
		setAllViewsInvisible();
		accountView.setVisible(true);
	}

	@FXML
	public void handleStatisticsClick() {
		setAllViewsInvisible();
		statisticsView.setVisible(true);
		//statisticsView.updateStatistics();
	}

	@FXML
	public void handleAddTransactionClick() {
		transactionAddController.setTransactionAddPage();
		setAllViewsInvisible();
		transactionAdd.setVisible(true);
	}

	/**
	 * Makes all views Invisible (useful for changing between views)
	 */
	public void setAllViewsInvisible() {
		transactionAdd.setVisible(false);
		accountView.setVisible(false);
		transactionList.setVisible(false);
		transactionDetail.setVisible(false);
		statisticsView.setVisible(false);
	}

}
