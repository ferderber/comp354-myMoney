package main.java.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import main.java.models.Type;
import main.java.services.Database;

/**
 * Provides Data Access methods for the Type Model.
 * 
 * @author Eric Morgan / Matthew Ferderber
 *
 */
public class TypeDao {
	private ConnectionSource connection;
	private Dao<Type, String> typeDao;

	/**
	 * Initializes a connection to the database and creates the table.
	 */
	public TypeDao() {
		try {
			// Get connection object from Database
			connection = Database.getConnection();
			// Create a ORMLite DAOManager for Type
			this.typeDao = DaoManager.createDao(connection, Type.class);
			// If the table doesn't exist create it
			TableUtils.createTableIfNotExists(connection, Type.class);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Retrieves a type from the database by ID.
	 * 
	 * @param id
	 *            Type's ID.
	 * @return Type object
	 */
	public Type getTypeById(String id) {
		try {
			return typeDao.queryForId(id);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieves all types from the database.
	 * 
	 * @return List of types
	 */
	public List<Type> getAllTypes() {
		try {
			return typeDao.queryForAll();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return new ArrayList<Type>();

	}

	/**
	 * Updates a type in the database.
	 * 
	 * @param type
	 *            Type Object
	 * @return number of types updated
	 */
	public int updateType(Type type) {
		try {
			return typeDao.update(type);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	/**
	 * Inserts a type into the database, but only if it doesn't already exist.
	 * 
	 * @param type
	 *            Type Object
	 * @return number of Types inserted
	 */
	public Type insert(Type type) {
		try {
			return typeDao.createIfNotExists(type);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return new Type("");
	}

	/**
	 * Deletes a type from the database.
	 * 
	 * @param type
	 *            Type Object
	 * @return number of Types deleted
	 */
	public int delete(Type type) {
		try {
			return typeDao.delete(type);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
/*	
	public List<Transaction> getAllofType(String type) {
		try {
			TransactionDao transDao = new TransactionDao();
			return transDao.queryForEq("type", type);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return new ArrayList<Transaction>();	
	}
*/
}