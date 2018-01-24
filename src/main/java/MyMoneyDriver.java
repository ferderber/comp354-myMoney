package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyMoneyDriver extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/MainView.fxml"));
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setTitle("MyMoney Application");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
