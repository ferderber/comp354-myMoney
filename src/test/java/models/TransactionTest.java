package test.java.models;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import main.java.models.Account;
import main.java.models.Transaction;

public class TransactionTest {

	@Test
	public void testFullContructor() {
		int id = 0;
		String name = "name";
		String description = "description";
		Date date = new Date();
		double amount = 10;
		Account fromAccount = new Account("Savings", new Date());
		Account toAccount = new Account("Chequing", new Date());
		Transaction t = new Transaction(id, name, description, date, amount, fromAccount, toAccount);
		assertEquals(t.getId(), id);
		assertEquals(t.getName(), name);
		assertEquals(t.getDescription(), description);
		assertEquals(t.getDate(), date);
		assertEquals(t.getAccountTo(), toAccount);
		assertEquals(t.getAccountFrom(), fromAccount);
	}

	@Test
	public void testPartialConstructor() {
		String name = "name";
		String description = "description";
		Date date = new Date();
		double amount = 10;
		Account fromAccount = new Account("Savings", new Date());
		Account toAccount = new Account("Chequing", new Date());
		Transaction t = new Transaction(name, description, date, amount, fromAccount, toAccount);
		assertEquals(t.getId(), 0);
		assertEquals(t.getName(), name);
		assertEquals(t.getDescription(), description);
		assertEquals(t.getDate(), date);
		assertEquals(t.getAccountTo(), toAccount);
		assertEquals(t.getAccountFrom(), fromAccount);
	}

}
