package main.java.service.sqlite;

public final class SQLiteTypes {
	/**
	 * Returns the most similar SQLite type for the given Java type
	 * 
	 * @param typeName
	 */
	public static String getSqlType(String typeName) {
		switch (typeName) {
		case "int":
		case "Integer":
			return "INTEGER";
		case "Double":
			return "DOUBLE";
		case "Date":
			return "DATE";
		default:
			return "TEXT";
		}
	}

}
