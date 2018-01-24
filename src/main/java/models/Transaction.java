package main.java.models;

import java.sql.Date;

import main.java.service.Table;

public class Transaction extends Table {
	public int id;
	public String name;
	public String description;
	public Date date;

	Transaction(int id, String name, String description, Date date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
	}

}
