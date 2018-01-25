package main.java.controllers;

import javafx.fxml.FXML;
import main.java.models.Transaction;

public class TransactionDetailController {
	private Transaction transaction;

	public TransactionDetailController() {
		System.out.println("In Transaction Detail Controller");
	}

	public TransactionDetailController(Transaction transaction) {
		this.transaction = transaction;
	}

	@FXML
	private void saveTransaction() {
		System.out.println("Saving transaction");
	}
}
