package main.java.services;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Singleton class that provides a single connection to the Database.
 * 
 * @author Matthew Ferderber
 *
 */
public class Database {
	public static final String DATABASE_URL = "jdbc:sqlite:my_money.db";
	public static ConnectionSource connection;
	public static final Database database = new Database();

	/**
	 * Initializes Database connection.
	 */
	private Database() {
		try {
			connection = new JdbcConnectionSource(DATABASE_URL);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Retrieves database ConnectionSource.
	 * 
	 * @return Database Connection
	 */
	public static ConnectionSource getConnection() {
		return connection;
	}

}
