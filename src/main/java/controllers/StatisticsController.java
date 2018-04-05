package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import main.Statistics.Statistics;
import main.Statistics.Stats_Interface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChartBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.dao.TransactionDao;
import main.java.models.Transaction;
import main.java.views.TransactionView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class StatisticsController implements Initializable {
	@FXML
	private VBox container;

	@FXML
	private List<String> transactionViews;

	@FXML
	private Text transactionName;

	@FXML
	private Button updateStatisticsButton;

	@FXML
	private TextField transactionType;

	@FXML
	private TextField transactionRecc;

	@FXML
	private ComboBox Types;

	@FXML
	private VBox BarChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TransactionDao dao = new TransactionDao();
		List<Transaction> transactions = dao.getAllTransactions();
		transactionViews = new ArrayList<String>();

		Statistics stat = new Statistics();

		List<String> name = stat.returnTypes(transactions);

		Types.getItems().addAll(name);

		Stats_Interface si = new Stats_Interface();

		main.Statistics.BarChart bco = si.displayYearStats(transactions);

		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);

		xAxis.setLabel("Months");
		yAxis.setLabel("Dollars");
		bc.setTitle("Year Statistics " + bco.Year);

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Average");

		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Median");

		XYChart.Series series3 = new XYChart.Series();
		series3.setName("Inflow");

		XYChart.Series series4 = new XYChart.Series();
		series4.setName("Outflow");

		System.out.println(bco.MonthNames.size());

		for (int i = 0; i < 12; i++) {
			System.out.println(bco.MonthNames.get(i) + " " + bco.MonthAvg.get(i));

			series1.getData().add(new XYChart.Data(bco.MonthNames.get(i), bco.MonthAvg.get(i)));
			series2.getData().add(new XYChart.Data(bco.MonthNames.get(i), bco.MonthMed.get(i)));
			series3.getData().add(new XYChart.Data(bco.MonthNames.get(i), bco.MonthIN.get(i)));
			series4.getData().add(new XYChart.Data(bco.MonthNames.get(i), bco.MonthOUT.get(i)));

			System.out.println(bco.MonthOUT.get(i));
		}

		bc.getData().addAll(series1, series2, series3, series4);
		BarChart.getChildren().add(bc);
	}

	public void updateStatistics(ActionEvent event) {
		TransactionDao dao = new TransactionDao();
		List<Transaction> transactions = dao.getAllTransactions();

		Stats_Interface inter = new Stats_Interface();

		String Temp = "";
		String type = transactionType.getText();
		String recc = transactionRecc.getText();
		String category = (String) Types.getValue();
		int rec = 0;

		if (!recc.equals("")) {
			rec = Integer.parseInt(recc);
		}

		if (type.equals("")) {

			List<String> list = inter.ExtremeMonths(transactions);

			for (int i = 0; i < list.size(); i++) {
				Temp += list.get(i) + "\n";
			}
		} else if ((!type.equals("")) && recc.equals("")) {

			List<String> list = inter.displayStats(transactions, type, category);

			if (category != null) {
				Temp += "Selection by Category " + category + "\n\n";
			}

			for (int i = 0; i < list.size(); i++) {
				Temp += list.get(i) + "\n";
			}
		} else if ((!type.equals("")) && (!recc.equals(""))) {
			List<String> list = inter.displayRecurring(transactions, type, rec, category);

			if (category != null) {
				Temp += "Selection by Category " + category + "\n\n";
			}

			for (int i = 0; i < list.size(); i++) {
				Temp += list.get(i) + "\n";
			}
		}

		transactionName.setText(Temp);
	}

}
