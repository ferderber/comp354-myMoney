package main.java.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import main.java.models.Account;
import main.java.services.Database;

/**
 * Provides Data Access methods for the Account Model.
 * 
 * @author Artem Khomich
 *
 */
public class AccountDao {
	private ConnectionSource connection;
	private Dao<Account, Integer> accountDao;

	/**
	 * Initializes a connection to the database and creates the table.
	 */
	public AccountDao() {
		try {
			// Get connection object from Database
			connection = Database.getConnection();
			// Create a ORMLite DAOManager for Account
			this.accountDao = DaoManager.createDao(connection, Account.class);
			// If the table doesn't exist create it
			TableUtils.createTableIfNotExists(connection, Account.class);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Retrieves a account from the database by ID.
	 * 
	 * @param id
	 *            Account's ID.
	 * @return Account object
	 */
	public Account getAccountById(int id) {
		try {
			return accountDao.queryForId(id);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieves all accounts from the database.
	 * 
	 * @return List of accounts
	 */
	public List<Account> getAllAccounts() {
		try {
			return accountDao.queryForAll();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return new ArrayList<Account>();

	}

	/**
	 * Updates a account in the database.
	 * 
	 * @param account
	 *            Account Object
	 * @return number of accounts updated
	 */
	public int updateAccount(Account account) {
		try {
			return accountDao.update(account);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	/**
	 * Inserts a account into the database.
	 * 
	 * @param account
	 *            Account Object
	 * @return number of Accounts inserted
	 */
	public int insert(Account account) {
		try {
			return accountDao.create(account);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	/**
	 * Deletes a account from the database.
	 * 
	 * @param account
	 *            Account Object
	 * @return number of Accounts deleted
	 */
	public int delete(Account account) {
		try {
			return accountDao.delete(account);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
}
