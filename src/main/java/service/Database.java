package main.java.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import main.java.models.Transaction;
import main.java.service.sqlite.SqlTable;

public class Database {

	private static final Database INSTANCE = new Database();
	private static Map<String, SqlTable> tableMap;

	private Database() {
		tableMap = new HashMap<String, SqlTable>();
		System.out.println("Initializing Database");
		Class<? extends Table>[] models = (Class<? extends Table>[]) new Class[] { Transaction.class };
		loadModelData(models);
	}

	private void loadModelData(Class<? extends Table>[] models) {
		for (int i = 0; i < models.length; i++) {
			SqlTable sqlTable = new SqlTable(models[i]);
			tableMap.put(sqlTable.getName(), sqlTable);
		}
	}

	private void createTable(Class<?> model) {
		SqlTable table = tableMap.get(model.getName());
		table.getCreateTableString();
		// use db driver to create table
	}

	public static Database getInstance() {
		return INSTANCE;
	}

	public <T extends Table> void insert(T model) {
		// use db driver to insert a row
	}

	public <T extends Table> void update(T model) {
		// user db driver to update a row
	}

}
