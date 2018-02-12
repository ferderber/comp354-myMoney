package main.java.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.java.dao.TransactionDao;
import main.java.models.Transaction;

public class TransactionAddController implements Initializable {

	@FXML
	private Button addTransactionButton;
	@FXML
	private GridPane addPane;
	@FXML
	private TextField nameField;
	@FXML
	private TextField amountField;
	@FXML
	private TextField descriptionField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void addTransaction() {
		TransactionDao dao = new TransactionDao();
		String name = nameField.getText();
		String description = descriptionField.getText();
		double amount = 0.0;
		try {
			amount = Double.parseDouble(amountField.getText());
		} catch (Exception ex) {
			// TODO: Display validation error
		}
		Transaction t = new Transaction(name, description, new Date(), amount);
		dao.insert(t);
		clearTransaction();
	}

	@FXML
	private void clearTransaction() {
		nameField.setText("");
		descriptionField.setText("");
		amountField.setText("");
	}

}
