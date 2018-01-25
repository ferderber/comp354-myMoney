package main.java.models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Transaction {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String name;
	@DatabaseField
	private String description;
	@DatabaseField
	private Date date;

	protected Transaction() {

	}

	public Transaction(String name, String description, Date date) {
		this.name = name;
		this.description = description;
		this.date = date;
	}

	public Transaction(int id, String name, String description, Date date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
	}

}
