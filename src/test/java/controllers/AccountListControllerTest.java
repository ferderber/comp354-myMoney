package test.java.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import javafx.collections.ObservableList;
import javafx.geometry.VerticalDirection;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.MyMoneyDriver;
import main.java.dao.AccountDao;
import main.java.dao.TransactionDao;

public class AccountListControllerTest extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {
		new MyMoneyDriver().start(stage);
	}

	@Before
	public void beforeEach() {
		clickOn("View Accounts");
	}

	@Test
	public void testAccountListClick() {
		assertTrue(lookup("#accountList").query().isVisible());
	}

	@Test
	public void testAccountItemClick() {
		AccountDao dao = new AccountDao();
		if (dao.getAllAccounts().size() > 0) {
			VBox list = lookup("#container").query();
			ObservableList<Node> items = list.getChildren();
			clickOn(items.get(0));
			assertTrue(lookup("#accountDetail").query().isVisible());
		}
	}

	@Test
	public void testScrollToBottom() {
		AccountDao dao = new AccountDao();
		if (dao.getAllAccounts().size() > 0) {
			ScrollPane pane = lookup(".account-list > ScrollPane").query();
			VBox list = lookup("#container").query();
			ObservableList<Node> items = list.getChildren();
			moveTo(items.get(0));
			scroll((int) items.get(0).getLayoutBounds().getHeight(), VerticalDirection.UP);
		}
	}
}