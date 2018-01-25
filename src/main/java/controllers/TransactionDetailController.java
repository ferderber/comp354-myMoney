package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.java.models.Transaction;

public class TransactionDetailController implements Initializable {

	@FXML
	private ObjectProperty<Transaction> transactionProperty = new SimpleObjectProperty<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setTransaction(Transaction transaction) {
		this.transactionProperty.set(transaction);
	}

	@FXML
	private void saveTransaction() {
		System.out.println("Saving transaction");
	}

	public ObjectProperty<Transaction> transactionProperty() {
		return transactionProperty;
	}

	public Transaction getTransaction() {
		return transactionProperty.get();
	}
}
