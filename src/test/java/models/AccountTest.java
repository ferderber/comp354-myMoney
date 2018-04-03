package test.java.models;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import main.java.models.Account;
import main.java.models.Enumerator.AccountType;

public class AccountTest {

	@Test
	public void testConstructor() {
		String name = "name";
		long number = 4178348989l;
		AccountType type = AccountType.credit;
		double balance = 1384.75d;
		Account t = new Account(name, number, type, balance);
		assertEquals(t.getId(), 0);
		assertEquals(t.getName(), name);
		assertEquals(t.getType(), type);
		//assertEquals(t.getBalance(), balance);
		assertEquals(t.getNumber(), number);
	}

}
