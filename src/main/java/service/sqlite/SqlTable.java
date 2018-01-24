package main.java.service.sqlite;

import java.lang.reflect.Field;

import main.java.service.Table;

public class SqlTable {

	private String name;
	private String simpleName;
	private Field[] fields;
	private String insertString;
	private String updateByIdString;
	private String deleteByIdString;
	private String createTableString;
	private String dropTableString;
	private static final String INSERT_TEMPLATE = "INSERT INTO %s(%s) VALUES (%s);";
	private static final String UPDATE_TEMPLATE = "UPDATE %s SET %s WHERE %s;";
	private static final String DELETE_TEMPLATE = "DELETE FROM %s WHERE %s;";
	private static final String CREATE_TABLE_TEMPLATE = "CREATE TABLE %s (%s);";
	private static final String DROP_TABLE_TEMPLATE = "DROP TABLE %s;";

	public SqlTable(Class<? extends Table> model) {
		this.name = model.getName();
		this.simpleName = model.getSimpleName();
		this.fields = model.getFields();
		this.insertString = String.format(INSERT_TEMPLATE, simpleName, getFieldNameString(), "%s");
		this.updateByIdString = String.format(UPDATE_TEMPLATE, simpleName, "%s", getFieldNameString());
		this.deleteByIdString = String.format(DELETE_TEMPLATE, simpleName, "%s");
		this.createTableString = String.format(CREATE_TABLE_TEMPLATE, simpleName, getFieldNameTypeString());
		this.dropTableString = String.format(DROP_TABLE_TEMPLATE, simpleName);
	}

	private String getFieldNameTypeString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < fields.length; i++) {
			str.append(fields[i].getName());
			str.append(" ");
			str.append(SQLiteTypes.getSqlType(fields[i].getType().getSimpleName()));
			if (i != fields.length - 1) {
				str.append(", ");
			}
		}
		return str.toString();
	}

	private String getFieldNameString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < fields.length; i++) {
			str.append(fields[i].getName());
			if (i != fields.length - 1) {
				str.append(", ");
			}
		}
		return str.toString();
	}

	public String getName() {
		return name;
	}

	public Field[] getFields() {
		return fields;
	}

	public String getUpdateByIdString() {
		return updateByIdString;
	}

	public String getDeleteByIdString() {
		return deleteByIdString;
	}

	public String getInsertString() {
		return insertString;
	}

	public String getCreateTableString() {
		return createTableString;
	}

	public String getDropTableString() {
		return dropTableString;
	}

}
