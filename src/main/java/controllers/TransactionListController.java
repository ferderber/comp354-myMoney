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

public class TransactionListController implements Initializable {

	@FXML
	private FlowPane container;

	private List<TransactionView> transactionViews;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		transactionViews = new ArrayList<TransactionView>();
		TransactionDao dao = new TransactionDao();
		List<Transaction> transactions = dao.getAllTransactions();
		transactions.forEach((transaction) -> {
			transactionViews.add(new TransactionView(transaction));
		});
		container.getChildren().addAll(transactionViews);

	}

	public void setOnTransactionViewClick(EventHandler<MouseEvent> handler) {
		transactionViews.forEach((tView) -> tView.setOnAction(handler));
	}

	@FXML
	private List<Transaction> getTransactions() {
		TransactionDao transactionDao = new TransactionDao();
		return transactionDao.getAllTransactions();
	}

}
