package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import main.java.dao.TransactionDao;
import main.java.dao.TypeDao;
import main.java.models.Transaction;
import main.java.models.Type;
import main.java.views.TransactionView;

/**
 * Provides a list of TransactionViews.
 * 
 * @author Matthew Ferderber
 *
 */
public class TransactionListController implements Initializable {

	@FXML
	private VBox container;
	
	@FXML
	private VBox containerType;

	private List<TransactionView> transactionViews;
	private List<TransactionView> transactionViewsByType;

	private EventHandler<MouseEvent> transactionViewActionHandler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		transactionViews = new ArrayList<TransactionView>();
		transactionViewsByType = new ArrayList<TransactionView>();
	}

	/**
	 * Sets the transactionClickHandler for each TransactionView
	 * 
	 * @param handler
	 */
	public void setOnTransactionViewClick(EventHandler<MouseEvent> handler) {
		transactionViewActionHandler = handler;
	}

	public void createTransactionList() {
		// remove all previous transactionViews
		transactionViews.clear();
		transactionViewsByType.clear();
		
		container.getChildren().clear();
		containerType.getChildren().clear();

		TransactionDao dao = new TransactionDao();
		TypeDao typeDao = new TypeDao();
		
		// Get all transactions from the database
		List<Transaction> transactions = dao.getAllTransactions();
		List<Type> types = typeDao.getAllTypes();
		
		// Create a TransactionView forEach transaction obj
		transactions.forEach((transaction) -> {
			transactionViews.add(new TransactionView(transaction, transactionViewActionHandler));
		});
		types.forEach((type) -> {
			transactionViewsByType.add(new TransactionView(type, transactionViewActionHandler));
		});
		
		// Add all of the transactions to the list
		container.getChildren().addAll(transactionViews);
		containerType.getChildren().addAll(transactionViewsByType);
	}

	@FXML
	private List<Transaction> getTransactions() {
		TransactionDao transactionDao = new TransactionDao();
		return transactionDao.getAllTransactions();
	}

}
