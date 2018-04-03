package main.java.models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import main.java.models.Enumerator;

@DatabaseTable
public class Account {
	@DatabaseField(generatedId = true)
	private int ID;
	@DatabaseField
	private String Name;
	@DatabaseField
	private long Number;
	@DatabaseField
	private Enumerator.AccountType Type;
	@DatabaseField
	private double Balance;
	// @DatabaseField
	// private String Description;

	// Entity tracking properties
	@DatabaseField
	private Date Create;
	@DatabaseField
	private Date Edit;
	@DatabaseField
	private Date Archived;
	@DatabaseField
	private String typeName;

	public Account() {

	}

	public Account(String name, long number, Enumerator.AccountType type, double balance) {
		this.Name = name;
		// this.Description = description;
		this.Number = number;
		this.Balance = balance;
		this.Type = type;
		this.Create = new Date();
		this.Edit = new Date();
		this.Archived = null;

	}

	@Override
	public boolean equals(Object obj) {
		Account a = (Account) obj;
		return a != null && ID == a.ID && Name.equals(a.Name) && Type.equals(a.Type) && Number == a.Number
				&& Create.equals(a.Create) && Edit.equals(a.Edit) && Balance == a.Balance;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public Enumerator.AccountType getType() {
		return Type;
	}

	public void setType(Enumerator.AccountType type) {
		this.Type = type;
	}

	public long getNumber() {
		return Number;
	}

	public void setNumber(long number) {
		this.Number = number;
	}

	public double getBalance() {
		return Balance;
	}

	public void setBalance(double balance) {
		this.Balance = balance;
	}

	public Date getCreate() {
		return Create;
	}

	public void setCreate(Date create) {
		this.Create = create;
	}

	public Date getEdit() {
		return Edit;
	}

	public void setEdit(Date edit) {
		this.Edit = edit;
	}

	public Date getArchived() {
		return Archived;
	}

	public void setArchived(Date archived) {
		this.Archived = archived;
	}

}
