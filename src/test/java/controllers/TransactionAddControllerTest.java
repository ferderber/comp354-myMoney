package test.java.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.collections.ObservableList;
import javafx.geometry.VerticalDirection;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.MyMoneyDriver;
import main.java.dao.TransactionDao;
import main.java.models.Transaction;

public class TransactionAddControllerTest extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {
		new MyMoneyDriver().start(stage);
	}

	@Before
	public void beforeEach() {
		clickOn("Add Transaction");
	}

	@Test
	public void testAddTransaction() {
		TransactionDao dao = new TransactionDao();
		TextField field = lookup("#nameField").query();
		clickOn(field);
		write("T");

		TextField typeField = lookup("#typeField").query();
		clickOn(typeField);
		write("ABC");

		TextField amountField = lookup("#amountField").query();
		clickOn(amountField);
		write("500");

		TextField descriptionField = lookup("#descriptionField").query();
		clickOn(descriptionField);
		write("Desc");

		clickOn("#addTransactionButton");

		List<Transaction> list = dao.getAllTransactions();
		boolean isFound = false;
		for (Transaction t : list) {
			if (t.getName().equals("T") && t.getAmount() == 500) {
				dao.delete(t);
				isFound = true;
				break;
			}
		}
		assertTrue(isFound);

	}

	@Test
	public void testInvalidAmount() {
		TransactionDao dao = new TransactionDao();
		TextField field = lookup("#nameField").query();
		clickOn(field);
		write("T");

		TextField typeField = lookup("#typeField").query();
		clickOn(typeField);
		write("ABC");

		// invalid amount
		TextField amountField = lookup("#amountField").query();
		clickOn(amountField);
		write("abc");

		TextField descriptionField = lookup("#descriptionField").query();
		clickOn(descriptionField);
		write("Desc");

		clickOn("#addTransactionButton");

		List<Transaction> list = dao.getAllTransactions();
		boolean isFound = false;
		for (Transaction t : list) {
			if (t.getName().equals("T")) {
				dao.delete(t);
				isFound = true;
				break;
			}
		}
		// no item should be found as the amount was invalid
		assertTrue(!isFound);
	}

	@Test
	public void testEmpty() {
		TransactionDao dao = new TransactionDao();
		List<Transaction> list = dao.getAllTransactions();
		int count = list.size();
		clickOn("#addTransactionButton");
		// nothing should be added
		assertEquals(count, dao.getAllTransactions().size());
	}
}
