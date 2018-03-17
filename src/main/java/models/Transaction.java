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
	@DatabaseField(foreign = true)
	private Type type;
	@DatabaseField
	private double amount;
	@DatabaseField
	private String description;
	@DatabaseField
	private Date date;
	@DatabaseField
	private int idAccount;


	protected Transaction() {

	}

	public Transaction(String name, Type type, double amount, String description, Date date,int AccId) {
		this.name = name;
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.date = date;
		this.idAccount=AccId;
	}

	public Transaction(int id, String name, Type type, double amount, String description, Date date,int AccId) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.date = date;
		this.idAccount=AccId;
	}

	@Override
	public boolean equals(Object obj) {
		Transaction t = (Transaction) obj;
		return t != null && id == t.id && name.equals(t.name) && type.equals(t.type) && description.equals(t.description)
				&& amount == t.amount && date.equals(t.date)&& idAccount==t.idAccount;
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
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

}