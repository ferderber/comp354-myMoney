package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Statistics.TransactionListCreator;

/**
 * Main class for the application. Initializes the MainView and displays the
 * application.
 * 
 * @author Matthew Ferderber
 *
 */
public class MyMoneyDriver extends Application {

	public static void main(String[] args) {

		//TransactionListCreator lc = new TransactionListCreator();

		//lc.delete();
		//lc.insert();
		Font.loadFont(MyMoneyDriver.class.getResource("/main/resources/css/OpenSans-CondLight.ttf").toExternalForm(),
				12);
		Font.loadFont(MyMoneyDriver.class.getResource("/main/resources/css/Comfortaa_Thin.ttf").toExternalForm(), 12);
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Set the title of the application
		primaryStage.setTitle("MyMoney Application");
		// Set the scene of the application to the new Scene
		primaryStage.setScene(createScene());
		primaryStage.setResizable(true);
		// Display the Stage
		primaryStage.show();
	}

	public Scene createScene() throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main/resources/view/MainView.fxml"));
		// Add the fxml Object to a new scene
		Scene scene = new Scene(root, 1000, 620);
		scene.getStylesheets().add("/main/resources/css/application.css");
		return scene;

	}
}
