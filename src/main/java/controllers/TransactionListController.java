package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import main.java.dao.TransactionDao;
import main.java.models.Transaction;
import main.java.views.TransactionView;

/**
 * Provides a list of TransactionViews.
 * 
 * @author Matthew Ferderber
 *
 */
public class TransactionListController implements Initializable {

	@FXML
	private FlowPane container;

	private List<TransactionView> transactionViews;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		transactionViews = new ArrayList<TransactionView>();
		TransactionDao dao = new TransactionDao();
		// Get all transactions from the database
		List<Transaction> transactions = dao.getAllTransactions();
		// Create a TransactionView forEach transaction obj
		transactions.forEach((transaction) -> {
			transactionViews.add(new TransactionView(transaction));
		});
		// Add all of the transactions to the list
		container.getChildren().addAll(transactionViews);

	}

	/**
	 * Sets the transactionClickHandler for each TransactionView
	 * 
	 * @param handler
	 */
	public void setOnTransactionViewClick(EventHandler<MouseEvent> handler) {
		transactionViews.forEach((tView) -> tView.setOnAction(handler));
	}

	@FXML
	private List<Transaction> getTransactions() {
		TransactionDao transactionDao = new TransactionDao();
		return transactionDao.getAllTransactions();
	}

}
