package test.java.controllers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.MyMoneyDriver;

public class MainController extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {
		new MyMoneyDriver().start(stage);
	}

	@Test
	public void testAccountListClick() {
		clickOn("View Transaction History");
		assertTrue(lookup("#transactionList").query().isVisible());
	}

	@Test
	public void testTransactionDetailClick() {
		clickOn("View Transaction History");
		clickOn(((VBox) lookup(".transaction-container").query()).getChildren().get(0));
		assertTrue(lookup("#transactionDetail").query().isVisible());
	}

	@Test
	public void testAddAccountClick() {
		clickOn("View Accounts");
		assertTrue(lookup("#accountContainer").query().isVisible());
	}

	@Test
	public void testAddTransactionClick() {
		clickOn("Add Transaction");
		assertTrue(lookup("#transactionAdd").query().isVisible());
	}
}
