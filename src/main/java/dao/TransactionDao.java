package main.java.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
	 * Retrieves a transaction from the database by the associated account's ID.
	 * 
	 * @param id
	 *            Transaction's ID.
	 * @return Transaction object
	 */
	public List<Transaction> getTransactionsByAccount(int accountId){
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("accountID", accountId);
			return transactionDao.queryForFieldValuesArgs(map);
		}catch(SQLException ex) {
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
	 * Creates item if it doesn't exist, or updates it if it does. For convenience and easy testing whether or not field exists.
	 * @param transaction
	 * @return A status object indicating whether the row was created or updated,
	 */
	public Dao.CreateOrUpdateStatus createOrUpdate(Transaction transaction) {
			try {
				return transactionDao.createOrUpdate(transaction);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return null;
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
	/**
	 * Closes underlying connection to the database.
	 * @throws IOException
	 */
	public void close() throws IOException{
		connection.close();
	}
	

}
