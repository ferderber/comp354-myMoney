package main.java.controllers;

import java.util.List;

import javafx.fxml.FXML;
import main.java.dao.TransactionDao;
import main.java.models.Transaction;

public class TransactionListController {

	public TransactionListController() {
		System.out.println("Creating Transaction List Controller");
	}

	/**
	 * Dispatches editTransaction event to MainController
	 * 
	 * @return
	 */
	@FXML
	private int editTransaction() {
		// TODO: Implement event dispatching.
		return 0;
	}

	@FXML
	private List<Transaction> getTransactions() {
		TransactionDao transactionDao = new TransactionDao();
		return transactionDao.getAllTransactions();
	}
}
