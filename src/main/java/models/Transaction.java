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
	private double amount;
	@DatabaseField
	private Date date;
	@DatabaseField(foreign = true, foreignColumnName = "id")
	private Account accountTo;
	@DatabaseField(foreign = true, foreignColumnName = "id")
	private Account accountFrom;

	protected Transaction() {

	}

	public Transaction(String name, String description, Date date, double amount, Account accountFrom,
			Account accountTo) {
		this.name = name;
		this.description = description;
		this.date = date;
		this.amount = amount;
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
	}

	public Transaction(int id, String name, String description, Date date, double amount, Account accountFrom,
			Account accountTo) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.amount = amount;
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
	}

	@Override
	public boolean equals(Object obj) {
		Transaction t = (Transaction) obj;
		return t != null && amount == t.amount && id == t.id && name.equals(t.name) && description.equals(t.description)
				&& date.equals(t.date);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Account getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(Account accountTo) {
		this.accountTo = accountTo;
	}

	public Account getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(Account accountFrom) {
		this.accountFrom = accountFrom;
	}

}
