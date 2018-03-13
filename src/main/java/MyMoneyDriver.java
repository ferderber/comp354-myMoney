package main.java;

import java.io.IOException;
import java.util.Date;

import com.j256.ormlite.dao.ForeignCollection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.java.dao.AccountDao;
import main.java.dao.TransactionDao;
import main.java.dao.TypeDao;
import main.java.models.Account;
import main.java.models.Enumerator.AccountType;
import main.java.models.Transaction;
import main.java.models.Type;

/**
 * Main class for the application. Initializes the MainView and displays the
 * application.
 * 
 * @author Matthew Ferderber
 *
 */
public class MyMoneyDriver extends Application {

	public static void main(String[] args) {
		
		Font.loadFont(MyMoneyDriver.class.getResource("/main/resources/css/OpenSans-CondLight.ttf").toExternalForm(), 12);
		Font.loadFont(MyMoneyDriver.class.getResource("/main/resources/css/Comfortaa_Regular.ttf").toExternalForm(), 12);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		/*
		 * AccountDao adao = new AccountDao(); adao.insert(new Account("Sample Account",
		 * 12345L, AccountType.debit,14)); adao.insert(new Account("Sample Account2",
		 * 12345L, AccountType.credit,3));
		 */
		// Create and Insert a sample element into the Transaction table

		/*
		TypeDao typeDao = new TypeDao();
		Type type = new Type("one");
		typeDao.insert(type);
		TransactionDao dao = new TransactionDao();
		dao.insert(new Transaction("Sample Transaction", type, Math.round(Math.random() * 50), "Sample Description", new Date()));
		*/


		
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
		Scene scene = new Scene(root, 600, 300);
		scene.getStylesheets().add("/main/resources/css/application.css");
		return scene;

	}
}
