package test.java.models;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import main.java.models.Transaction;

public class TransactionTest {

	@Test
	public void testFullContructor() {
		int id = 0;
		String name = "name";
		String description = "description";
		Date date = new Date();
		Transaction t = new Transaction(id, name, description, date);
		assertEquals(t.getId(), id);
		assertEquals(t.getName(), name);
		assertEquals(t.getDescription(), description);
		assertEquals(t.getDate(), date);
	}

	@Test
	public void testPartialConstructor() {
		String name = "name";
		String description = "description";
		Date date = new Date();
		Transaction t = new Transaction(name, description, date);
		assertEquals(t.getId(), 0);
		assertEquals(t.getName(), name);
		assertEquals(t.getDescription(), description);
		assertEquals(t.getDate(), date);
	}

}
