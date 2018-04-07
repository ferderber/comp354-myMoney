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
import main.java.dao.AccountDao;
import main.java.dao.TransactionDao;
import main.java.models.Account;
import main.java.models.Enumerator.AccountType;
import main.java.models.Transaction;

public class AccountAddControllerTest extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {
		new MyMoneyDriver().start(stage);
	}

	@Before
	public void beforeEach() {
		clickOn("Add Account");
	}

	@Test
	public void testAddAccount() {
		AccountDao dao = new AccountDao();
		TextField field = lookup("#nameField").query();
		clickOn(field);
		write("name");

		TextField numberField = lookup("#numberField").query();
		clickOn(numberField);
		write("4003532");

		TextField typeField = lookup("#typeField").query();
		clickOn(typeField);
		write("debit");

		TextField balanceField = lookup("#balanceField").query();
		clickOn(balanceField);
		write("3");

		clickOn("#addAccountButton");

		List<Account> list = dao.getAllAccounts();
		boolean isFound = false;
		for (Account t : list) {
			if (t.getName().equals("name") && t.getBalance() == 3) {
				dao.delete(t);
				isFound = true;
				break;
			}
		}
		assertTrue(isFound);

	}

	@Test
	public void testInvalidNumber() {
		AccountDao dao = new AccountDao();
		TextField field = lookup("#nameField").query();
		clickOn(field);
		write("name");

		TextField numberField = lookup("#numberField").query();
		clickOn(numberField);
		write("-4003532");

		TextField typeField = lookup("#typeField").query();
		clickOn(typeField);
		write("debit");

		TextField balanceField = lookup("#balanceField").query();
		clickOn(balanceField);
		write("3");

		clickOn("#addAccountButton");

		List<Account> list = dao.getAllAccounts();
		boolean isFound = false;
		for (Account t : list) {
			if (t.getName().equals("name")) {
				dao.delete(t);
				isFound = true;
				break;
			}
		}
		// no item should be found as the number was invalid
		assertTrue(!isFound);
	}

	@Test
	public void testEmpty() {
		AccountDao dao = new AccountDao();
		List<Account> list = dao.getAllAccounts();
		int count = list.size();
		clickOn("#addAccountButton");
		// nothing should be added
		assertEquals(count, dao.getAllAccounts().size());
	}
}