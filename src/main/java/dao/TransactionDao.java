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

public class TransactionDao {
	private ConnectionSource connection;
	private Dao<Transaction, Integer> transactionDao;

	public TransactionDao() {
		try {
			connection = Database.getConnection();
			this.transactionDao = DaoManager.createDao(connection, Transaction.class);
			TableUtils.createTableIfNotExists(connection, Transaction.class);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public Transaction getTransactionById(int id) {
		try {
			return transactionDao.queryForId(id);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<Transaction> getAllTransactions() {
		try {
			return transactionDao.queryForAll();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return new ArrayList<Transaction>();

	}

	public int updateTransaction(Transaction transaction) {
		try {
			return transactionDao.update(transaction);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public int insert(Transaction transaction) {
		try {
			return transactionDao.create(transaction);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public int delete(Transaction transaction) {
		try {
			return transactionDao.delete(transaction);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
}
