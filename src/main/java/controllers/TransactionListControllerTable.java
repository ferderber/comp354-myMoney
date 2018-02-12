package main.java.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import main.java.controllers.MainController.TransactionRowClickHandler;
import main.java.dao.TransactionDao;
import main.java.models.Transaction;

public class TransactionListControllerTable implements Initializable{
	
	@FXML
	private TableView<Transaction> transacDataTable;
	@FXML
	private AnchorPane anchor;
	@FXML
	private TableColumn<Transaction, String> nameCol;
	@FXML
	private TableColumn<Transaction, String> amountCol;
	private TransactionRowClickHandler handler;//Needs to be passed in from Main Controller and not created in here. Otherwise a null pointer exception will occur, since the anonymous instance used will have no corresponding TransactionListController.
	//Even if it was, it wouldn't be THIS instance of TransactionListController.
	
	public TransactionListControllerTable() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TransactionDao dao = new TransactionDao();
		// Get all transactions from the database
		List<Transaction> transactions = dao.getAllTransactions();//TODO: Make this retrieve only ones associated with the current account's ID.
		transacDataTable.setRowFactory(rc->{
			TableRow<Transaction> row = new TableRow<Transaction>();
			row.setOnMouseClicked(handler);
			return row;
		});
		nameCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("name"));
		amountCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
		// Add all of the transactions to the table
		transacDataTable.setItems(FXCollections.observableArrayList(transactions));
		
	}
	protected void setHandler(TransactionRowClickHandler handler){
		this.handler = handler;
	}
}
