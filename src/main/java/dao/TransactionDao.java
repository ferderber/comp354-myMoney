package main.java.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import main.java.models.Transaction;
import main.java.services.Database;

/**
 * Provides Data Access methods for the Transaction Model.
 * 
 * @author Matthew Ferderber
 *
 */
public class TransactionDao {
	private ConnectionSource connection;
	private Dao<Transaction, Integer> transactionDao;

	/**
	 * Initializes a connection to the database and creates the table.
	 */
	public TransactionDao() {
		try {
			// Get connection object from Database
			connection = Database.getConnection();
			// Create a ORMLite DAOManager for Transaction
			this.transactionDao = DaoManager.createDao(connection, Transaction.class);
			// If the table doesn't exist create it
			TableUtils.createTableIfNotExists(connection, Transaction.class);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Retrieves a transaction from the database by ID.
	 * 
	 * @param id
	 *            Transaction's ID.
	 * @return Transaction object
	 */
	public Transaction getTransactionById(int id) {
		try {
			return transactionDao.queryForId(id);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieves all transactions from the database.
	 * 
	 * @return List of transactions
	 */
	public List<Transaction> getAllTransactions() {
		try {
			return transactionDao.queryForAll();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return new ArrayList<Transaction>();

	}

	/**
	 * Updates a transaction in the database.
	 * 
	 * @param transaction
	 *            Transaction Object
	 * @return number of transactions updated
	 */
	public int updateTransaction(Transaction transaction) {
		try {
			return transactionDao.update(transaction);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	/**
	 * Inserts a transaction into the database.
	 * 
	 * @param transaction
	 *            Transaction Object
	 * @return number of Transactions inserted
	 */
	public int insert(Transaction transaction) {
		try {
			return transactionDao.create(transaction);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public int insert(Transaction[] transaction) {
		try {
				for(int i=0; i<transaction.length-1; i++)
				{
					transactionDao.create(transaction[i]);
				}
			return transactionDao.create(transaction[transaction.length-1]);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Deletes a transaction from the database.
	 * 
	 * @param transaction
	 *            Transaction Object
	 * @return number of Transactions deleted
	 */
	public int delete(Transaction transaction) {
		try {
			return transactionDao.delete(transaction);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
	//vik- get all transactions associated with given account number
		public List<Transaction> getAllTransactionsById(int num){
			List <Transaction> all = getAllTransactions();
			List<Transaction> filtered = new ArrayList<>();
			for (Transaction item: all){
				if (item.getIdAccount()==num){
					filtered.add(item);}
			}
			return filtered;
		}
}
