package test.java.models;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import main.java.models.Transaction;
import main.java.models.Type;

public class TransactionTest {

	@Test
	public void testFullContructor() {
		int id = 0;
		String name = "name";
		Type type = new Type("type");
		double amount = 10;
		String description = "description";
		Date date = new Date();
		Transaction t = new Transaction(id, name, type, amount, description, date);
		assertEquals(t.getId(), id);
		assertEquals(t.getName(), name);
		assertEquals(t.getType(), type);
		assertEquals(t.getDescription(), description);
		assertEquals(t.getDate(), date);
	}

	@Test
	public void testPartialConstructor() {
		String name = "name";
		Type type = new Type("type");
		double amount = 10;
		String description = "description";
		Date date = new Date();
		Transaction t = new Transaction(name, type, amount, description, date);
		assertEquals(t.getId(), 0);
		assertEquals(t.getName(), name);
		assertEquals(t.getType(), type);
		assertEquals(t.getDescription(), description);
		assertEquals(t.getDate(), date);
	}

}
