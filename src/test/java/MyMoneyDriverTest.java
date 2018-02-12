package test.java;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;
import main.java.MyMoneyDriver;

public class MyMoneyDriverTest extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {
		new MyMoneyDriver().start(stage);
	}

	@Test
	public void testWindowExists() {
		// Main window should exist
		assertTrue(this.listTargetWindows().size() == 1);
	}
}
