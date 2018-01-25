package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import main.java.models.Transaction;
import main.java.views.TransactionView;

public class MainController implements Initializable {
	@FXML
	private TransactionListController transactionListController;
	@FXML
	private TransactionDetailController transactionDetailController;
	@FXML
	private Pane transactionList;
	@FXML
	private Pane transactionDetail;

	@FXML
	private SplitPane mainView;

	@FXML
	private StackPane detailPane;

	public MainController() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainView.setDividerPosition(0, 0.3);
		transactionList.setVisible(true);
		transactionDetail.setVisible(false);
		transactionListController.setOnTransactionViewClick(new TransactionViewClickHandler());
	}

	private void switchDetailView() {
		transactionList.setVisible(!transactionList.isVisible());
		transactionDetail.setVisible(!transactionDetail.isVisible());
	}

	public class TransactionViewClickHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			TransactionView transactionView = (TransactionView) event.getSource();
			Transaction t = transactionView.getTransaction();
			transactionDetailController.setTransaction(t);
			switchDetailView();
		}

	}

}
