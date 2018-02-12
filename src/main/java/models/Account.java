package main.java.models;

import java.util.Date;

import java.util.ArrayList;
import java.text.NumberFormat;//for currencyFormat
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import main.java.models.Enumerator;
 
@DatabaseTable
public class Account {
	@DatabaseField(generatedId = true, unique = true)
	private int ID;
	@DatabaseField
	private String Name;
	@DatabaseField
	private long Number;
	@DatabaseField
	private Enumerator.AccountType Type;
	@DatabaseField
	private double Balance;
	//Fill with DAO Select statement
	private ArrayList<Transaction> associatedTransactions;
	//@DatabaseField
	//private String Description;

	//Entity tracking properties
	@DatabaseField
	private Date Create;
	@DatabaseField
	private Date Edit;
	@DatabaseField
	private Date Archived;

	public Account() {

	}

	public Account(String name, long number, Enumerator.AccountType type, double balance) {
		this.Name = name;
		//this.Description = description;
		this.Number = number;
		this.Balance = balance;
		this.Type = type;
		this.Create = new Date();
		this.Edit = new Date();
		this.Archived = null;

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

	//public String getDescription() {
	//	return Description;
	//}

	//public void setDescription(String description) {
	//	this.Description = description;
	//}
	
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
	
	private void addTransactions(Transaction... trs){
		this.associatedTransactions.addAll(trs);
	}
	
	//Here to standardize formatting of currency strings across code. Default behavior is to round to the nearest cent.
	public static String currencyFormat(double amount) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.CANADA);
		return formatter.format(amount);
	}

}
