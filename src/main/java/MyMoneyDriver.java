package main.java;

import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.dao.TransactionDao;
import main.java.models.Transaction;

public class MyMoneyDriver extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TransactionDao dao = new TransactionDao();
		dao.insert(new Transaction("Sample Transaction", "Sample Description", new Date()));
		Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/MainView.fxml"));
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setTitle("MyMoney Application");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
