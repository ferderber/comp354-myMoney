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
import main.java.dao.TypeDao;
import main.java.models.Transaction;
import main.java.models.Type;

public class TransactionAddController implements Initializable {

	@FXML
	private Button addTransactionButton;
	@FXML
	private GridPane transactionAdd;
	@FXML
	private TextField nameField;
	@FXML
	private TextField typeField;
	@FXML
	private TextField amountField;
	@FXML
	private TextField descriptionField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void addTransaction() {
		TransactionDao transDao = new TransactionDao();
		TypeDao typeDao = new TypeDao();
		
		String name = nameField.getText();
		Type type = new Type(typeField.getText());
		String description = descriptionField.getText();
		double amount = 0.0;
		try {
			amount = Double.parseDouble(amountField.getText());
		} catch (Exception ex) {
			// TODO: Display validation error
		}
		
		Transaction t = new Transaction(name, typeDao.insert(type), amount, description, new Date());
		transDao.insert(t);
		
		clearTransaction();
	}

	@FXML
	private void clearTransaction() {
		nameField.setText("");
		typeField.setText("");
		descriptionField.setText("");
		amountField.setText("");
	}

}
