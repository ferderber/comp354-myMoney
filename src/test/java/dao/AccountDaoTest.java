package test.java.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.dao.AccountDao;
import main.java.models.Account;
import main.java.models.Enumerator.AccountType;
//import main.java.models.Type;

public class AccountDaoTest {

	private static boolean dbExists = false;
	private static final String PROJECT_PATH = System.getProperty("user.dir");
	private static final String DB_PATH = PROJECT_PATH + "/my_money.db";

	private static final Account testAccount1 = new Account("sample Account1", 11111111, AccountType.debit, 5);
	private static final Account testAccount2 = new Account("sample Account2", 33333333, AccountType.credit, -40);
	private static final Account testAccount3 = new Account("sample Account3", 4444444, AccountType.debit, 0);
	private static final Account testAccount4 = new Account("sample Account4", 123489034, AccountType.credit, 180);
	private static AccountDao dao;

	@BeforeClass
	public static void setUpClass() throws Exception {
		// Rename existing DB to backup
		File f = new File(DB_PATH);
		if (f.exists()) {
			f.renameTo(new File(PROJECT_PATH + "/backup.db"));
			dbExists = true;
		}
		dao = new AccountDao();

	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		// Delete test DB and rename backup db
		File realDB = new File(PROJECT_PATH + "/backup.db");
		File testDB = new File(DB_PATH);
		if (dbExists) {
			if (testDB.exists()) {
				testDB.delete();
			}
			realDB.renameTo(new File(DB_PATH));
		}
	}

	@Before
	public void setUp() throws Exception {
		dao.insert(testAccount1);
		dao.insert(testAccount2);
		dao.insert(testAccount3);
	}

	@After
	public void tearDown() throws Exception {
		dao.delete(testAccount1);
		dao.delete(testAccount2);
		dao.delete(testAccount3);
	}

	@Test
	public void testInsert() {
		// DAO should insert one transaction
		assertEquals(dao.insert(testAccount4), 1);
		assertEquals(dao.insert(testAccount4), 1);
		// ID should not be 0 (that means it wasn't assigned an ID)
		assertNotEquals(testAccount4.getId(), 0);
		// retrieved transaction should be the
		assertEquals(dao.getAccountById(testAccount4.getId()), testAccount4);
		dao.delete(testAccount4);
	}

	@Test
	public void testGetAll() {
		// getAll should return 4 objects (from setUp)
		List<Account> accounts = dao.getAllAccounts();
		assertArrayEquals(accounts.toArray(),
				new Account[] {  testAccount1, testAccount2, testAccount3 });
	}

	@Test
	public void testDelete() {
		// should delete account
		assertEquals(dao.delete(testAccount2), 1);
		List<Account> accounts = dao.getAllAccounts();
		assertArrayEquals(accounts.toArray(),
				new Account[] {  testAccount1, testAccount3 });
		// should not delete non-existent account
		assertEquals(dao.delete(testAccount2), 0);
		dao.insert(testAccount2);
	}

}