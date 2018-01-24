package main.java.service;

public abstract class Table {

	public static <T extends Table> void update(T model) {
		Database.getInstance().insert(model);
	}

	public static <T extends Table> void insert(T model) {
		Database.getInstance().update(model);
	}
}
