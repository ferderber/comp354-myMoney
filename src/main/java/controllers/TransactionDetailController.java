package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import main.java.dao.TransactionDao;
import main.java.models.Transaction;

/**
 * Displays a detailed view of a transaction. Allows the user to update the
 * transaction's details.
 * 
 * @author Matthew Ferderber
 *
 */
public class TransactionDetailController implements Initializable {

	@FXML
	private ObjectProperty<Transaction> transactionProperty = new SimpleObjectProperty<>();
	@FXML
	private Button editTransactionButton;
	@FXML
	private Button saveTransactionButton;
	@FXML
	private FlowPane editPane;
	@FXML
	private FlowPane detailPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setTransaction(Transaction transaction) {
		this.transactionProperty.set(transaction);
	}

	@FXML
	private void saveTransaction() {
		TransactionDao dao = new TransactionDao();
		dao.updateTransaction(transactionProperty.get());
		editPane.setVisible(false);
		detailPane.setVisible(true);
	}

	@FXML
	private void editTransaction() {
		editPane.setVisible(true);
		detailPane.setVisible(false);
	}

	public ObjectProperty<Transaction> transactionProperty() {
		return transactionProperty;
	}

	public Transaction getTransaction() {
		return transactionProperty.get();
	}
}
