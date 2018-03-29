package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.j256.ormlite.dao.ForeignCollection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.dao.TransactionDao;
import main.java.dao.TypeDao;
import main.java.models.Transaction;
import main.java.models.Type;
import main.java.views.TransactionView;

/**
 * Provides a list of TransactionViews.
 * 
 * @author Matthew Ferderber
 *
 */
public class TransactionListController implements Initializable {

	@FXML
	private VBox container;

	@FXML
	private VBox containerType;

	@FXML
	private VBox containerCharts;

	private List<TransactionView> transactionViews;
	private List<TransactionView> transactionViewsByType;

	private EventHandler<MouseEvent> transactionViewActionHandler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		transactionViews = new ArrayList<TransactionView>();
		transactionViewsByType = new ArrayList<TransactionView>();
	}

	/**
	 * Sets the transactionClickHandler for each TransactionView
	 * 
	 * @param handler
	 */
	public void setOnTransactionViewClick(EventHandler<MouseEvent> handler) {
		transactionViewActionHandler = handler;
	}

	public void createTransactionList() {
		// remove all previous transactionViews
		transactionViews.clear();
		transactionViewsByType.clear();

		container.getChildren().clear();
		containerType.getChildren().clear();
		containerCharts.getChildren().clear();

		TransactionDao dao = new TransactionDao();
		TypeDao typeDao = new TypeDao();
		ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();

		// Get all transactions from the database
		List<Transaction> transactions = dao.getAllTransactions();
		List<Type> types = typeDao.getAllTypes();

		// Create a TransactionView forEach transaction obj
		transactions.forEach((transaction) -> {
			transactionViews.add(new TransactionView(transaction, transactionViewActionHandler));
		});

		types.forEach((type) -> {
			transactionViewsByType.add(new TransactionView(type));
			ForeignCollection<Transaction> allTrans = type.getTransactions();
			double totalAmount = 0;
			for (Transaction t : allTrans) { // each transaction of that type
				transactionViewsByType.add(new TransactionView(t, transactionViewActionHandler));
				totalAmount += t.getAmount();
			}
			chartData.add(new PieChart.Data(type.getId(), totalAmount));
		});

		// Add all of the transactions to the list
		container.getChildren().addAll(transactionViews);
		containerType.getChildren().addAll(transactionViewsByType);

		final PieChart chart = new PieChart(chartData);
		chart.setTitle("Transactions by Categories");
		containerCharts.getChildren().add(chart);
	}

	@FXML
	private List<Transaction> getTransactions() {
		TransactionDao transactionDao = new TransactionDao();
		return transactionDao.getAllTransactions();
	}

}
