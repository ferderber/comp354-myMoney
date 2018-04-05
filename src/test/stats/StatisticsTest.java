package test.stats;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;
import main.Statistics.TransactionListCreator;
import main.java.MyMoneyDriver;

public class StatisticsTest extends ApplicationTest 
{

	public void start(Stage stage) throws Exception {
		new MyMoneyDriver().start(stage);
	}
	
	/*@Before
	public void setUp()
	{
		TransactionListCreator ls = new TransactionListCreator();
		ls.insert();
	}*/
	
	@Test
	public void testStatisticsClick() {
		clickOn("Statistics");
		assertTrue(lookup("#statistics").query().isVisible());
		clickOn("Submit");
		assertTrue(lookup("#transactionName").query().isVisible());
	}
	
	@Test
	public void testStatisticsType()
	{
		clickOn("Statistics");
		assertTrue(lookup("#statistics").query().isVisible());
		clickOn("Choose Type");
		assertTrue(lookup("#transactionName").query().isVisible());
	}
	
	@Test
	public void testStatisticsTypeChicken()
	{
		clickOn("Statistics");
		assertTrue(lookup("#statistics").query().isVisible());
		clickOn("Choose Type");
		clickOn("Lemon Type");
		clickOn("Submit");
		assertTrue(lookup("#transactionName").query().isVisible());
	}
	
	
	/*@Test
	public void testStatisticsAllClick() {
		clickOn("Statistics");
		assertTrue(lookup("#statistics").query().isVisible());
		clickOn("#transactionType");
		write("All");
		clickOn("Submit");
		assertTrue(lookup("#transactionName").query().isVisible());
	}
	
	@Test
	public void testStatisticsYearClick() {
		clickOn("Statistics");
		assertTrue(lookup("#statistics").query().isVisible());
		clickOn("#transactionType");
		write("2017");
		clickOn("Submit");
		assertTrue(lookup("#transactionName").query().isVisible());
	}
	
	@Test
	public void testStatisticsMonthClick() {
		clickOn("Statistics");
		assertTrue(lookup("#statistics").query().isVisible());
		clickOn("#transactionType");
		write("March 2017");
		clickOn("Submit");
		assertTrue(lookup("#transactionName").query().isVisible());
	}
	
	@Test
	public void testStatisticsSectionClick() {
		clickOn("Statistics");
		assertTrue(lookup("#statistics").query().isVisible());
		clickOn("#transactionType");
		write("6");
		clickOn("Submit");
		assertTrue(lookup("#transactionName").query().isVisible());
	}
	
	@Test
	public void testRecurringAllClick() {
		clickOn("Statistics");
		assertTrue(lookup("#statistics").query().isVisible());
		clickOn("#transactionType");
		write("All");
		clickOn("#transactionRecc");
		write("2");
		clickOn("Submit");
		assertTrue(lookup("#transactionName").query().isVisible());
	}
	
	@Test
	public void testRecurringYearClick() {
		clickOn("Statistics");
		assertTrue(lookup("#statistics").query().isVisible());
		clickOn("#transactionType");
		write("2017");
		clickOn("#transactionRecc");
		write("2");
		clickOn("Submit");
		assertTrue(lookup("#transactionName").query().isVisible());
	}
	
	@Test
	public void testRecurringMonthClick() {
		clickOn("Statistics");
		assertTrue(lookup("#statistics").query().isVisible());
		clickOn("#transactionType");
		write("March 2017");
		clickOn("#transactionRecc");
		write("2");
		clickOn("Submit");
		assertTrue(lookup("#transactionName").query().isVisible());
	}
	
	@Test
	public void testRecurringSectionClick() {
		clickOn("Statistics");
		assertTrue(lookup("#statistics").query().isVisible());
		clickOn("#transactionType");
		write("6");
		clickOn("#transactionRecc");
		write("2");
		clickOn("Submit");
		assertTrue(lookup("#transactionName").query().isVisible());
	}
	
	@After
	public void tearDown() throws Exception {
		TransactionListCreator ls = new TransactionListCreator();
		ls.delete();
	}*/

}
