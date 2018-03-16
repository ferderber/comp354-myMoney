package test.java.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.dao.TransactionDao;
import main.java.dao.TypeDao;
import main.java.models.Transaction;
import main.java.models.Type;

public class TypeDaoTest {
	
	private static boolean dbExists = false;
	private static final String PROJECT_PATH = System.getProperty("user.dir");
	private static final String DB_PATH = PROJECT_PATH + "/my_money.db";
	
	private static final Type test1 = new Type("Food");
	private static final Type test2 = new Type("Rent");
	private static final Type test3 = new Type("Entertainment");
	private static final Type test4 = new Type("Food");
	private static final Type test5 = new Type("Clothes");
	private static Transaction test6;
	
	private static TransactionDao tranDao;
	private static TypeDao typeDao;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		// Rename existing DB to backup
		File f = new File(DB_PATH);
		if (f.exists()) {
			f.renameTo(new File(PROJECT_PATH + "/backup.db"));
			dbExists = true;
		}
		tranDao = new TransactionDao();
		typeDao = new TypeDao();

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
		
		typeDao.insert(test1);
		typeDao.insert(test3);
		typeDao.insert(test5);
		
	}

	@After
	public void tearDown() throws Exception {
		tranDao.delete(test6);
		typeDao.delete(test1);
		typeDao.delete(test2);
		typeDao.delete(test3);
		typeDao.delete(test4);
		typeDao.delete(test5);
	}

	@Test
	public void testInsert() {
		typeDao.insert(test1);
		assertEquals(typeDao.getAllTypes().size(), 3);
		
		// test that inserting a Type that already exists does not get added to the table
		typeDao.insert(test4);
		assertEquals(typeDao.getAllTypes().size(), 3);
		
		typeDao.insert(test2);
		assertEquals(typeDao.getAllTypes().size(), 4);
	}

	
	@Test
	public void testGetForeign() {
		test6 = new Transaction("test", test1, 5, "d", new Date(),1);
		
		// test that adding new transactions with a type matching one in the Type table are added to that Type's ForeignCollection
		tranDao.insert(test6);
		assertEquals(typeDao.getTypeById(test1.getId()).getTransactions().size(), 1);
		
		tranDao.insert(test6);
		assertEquals(typeDao.getTypeById(test1.getId()).getTransactions().size(), 2);
		
	}
	
	
	@Test
	public void testDelete() {
		// should delete type
		assertEquals(typeDao.delete(test1), 1);
		
		// should not delete non-existent type
		assertEquals(typeDao.delete(test1), 0);

	}
	
}
