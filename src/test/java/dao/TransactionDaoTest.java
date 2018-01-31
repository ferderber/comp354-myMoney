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

import main.java.dao.TransactionDao;
import main.java.models.Transaction;

public class TransactionDaoTest {

	private static boolean dbExists = false;
	private static final String PROJECT_PATH = System.getProperty("user.dir");
	private static final String DB_PATH = PROJECT_PATH + "/my_money.db";

	private static final Transaction testTransaction1 = new Transaction("sample transaction", "description", new Date(),
			55);
	private static final Transaction testTransaction2 = new Transaction(55, "name", "description", new Date(), 2);
	private static final Transaction testTransaction3 = new Transaction("test", "d", new Date(), 3);
	private static final Transaction testTransaction4 = new Transaction("test", "d", new Date(), 4);
	private static final Transaction testTransaction5 = new Transaction("test", "d", new Date(), 5);
	private static TransactionDao dao;

	@BeforeClass
	public static void setUpClass() throws Exception {
		// Rename existing DB to backup
		File f = new File(DB_PATH);
		if (f.exists()) {
			f.renameTo(new File(PROJECT_PATH + "/backup.db"));
			dbExists = true;
		}
		dao = new TransactionDao();

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
		dao.insert(testTransaction2);
		dao.insert(testTransaction3);
		dao.insert(testTransaction4);
		dao.insert(testTransaction5);
	}

	@After
	public void tearDown() throws Exception {
		dao.delete(testTransaction2);
		dao.delete(testTransaction3);
		dao.delete(testTransaction4);
		dao.delete(testTransaction5);
	}

	@Test
	public void testInsert() {
		// DAO should insert one transaction
		assertEquals(dao.insert(testTransaction1), 1);
		assertEquals(dao.insert(testTransaction2), 1);
		// ID should not be 0 (that means it wasn't assigned an ID)
		assertNotEquals(testTransaction1.getId(), 0);
		// retrieved transaction should be the
		assertEquals(dao.getTransactionById(testTransaction1.getId()), testTransaction1);
	}

	@Test
	public void testGetAll() {
		// getAll should return 4 objects (from setUp)
		List<Transaction> transactions = dao.getAllTransactions();
		assertArrayEquals(transactions.toArray(),
				new Transaction[] { testTransaction2, testTransaction3, testTransaction4, testTransaction5 });
	}

	@Test
	public void testDelete() {
		// should delete transaction
		assertEquals(dao.delete(testTransaction2), 1);
		List<Transaction> transactions = dao.getAllTransactions();
		assertArrayEquals(transactions.toArray(),
				new Transaction[] { testTransaction3, testTransaction4, testTransaction5 });
		// should not delete non-existent transaction
		assertEquals(dao.delete(testTransaction2), 0);
	}

}
