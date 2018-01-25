package main.java.controllers;

import javafx.fxml.FXML;

public class MainController {
	@FXML
	private TransactionListController transactionListController;
	@FXML
	private TransactionDetailController transactionDetailController;

	public MainController() {
		System.out.println("In main controller");
		// Add event listeners here
	}
}
