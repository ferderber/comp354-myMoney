package test.java.controllers;

import static org.junit.Assert.assertTrue;

import java.awt.Robot;
import java.awt.event.MouseEvent;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.MyMoneyDriver;

public class MainController extends ApplicationTest {
	Robot robot;

	@Override
	public void start(Stage stage) throws Exception {
		robot=new Robot();
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
	public void testAccountButtonPresent() {
		clickOn("View Accounts");
		assertTrue(lookup("#NewAccountButton").query().isVisible());
	}

	@Test
	public void testAddTransactionClick() {
		clickOn("Add Transaction");
		assertTrue(lookup("#transactionAdd").query().isVisible());
	}
	
/*	@Test
	public void testAddAccountClick(){
		klick(200,60);
	}
	
	public void klick(int x, int y){
		robot.mouseMove(x, y);
		robot.delay(5);
		//robot.mousePress(MouseEvent.BUTTON1_MASK);
		//robot.mouseRelease(MouseEvent.BUTTON1_MASK);
		
	}*/
}
