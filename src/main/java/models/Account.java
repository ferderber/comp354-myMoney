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
	//@DatabaseField
	//private String Description;

	//Entity tracking properties
	@DatabaseField
	private Date Create;
	@DatabaseField
	private Date Edit;
	@DatabaseField
	private Date Archived;
	@DatabaseField
	private String typeName;
	
	//budget goal properties
	@DatabaseField
	private String GoalBalance;
	@DatabaseField
	private String DateBy;
	@DatabaseField
	private String Salary;
	
	public Account() {
	}

	public Account(String name, long number, Enumerator.AccountType type, double balance, String goalBalance, String dateBy, String salary) {
		this.Name = name;
		//this.Description = description;
		this.Number = number;
		this.Balance = balance;
		this.Type = type;
		this.Create = new Date();
		this.Edit = new Date();
		this.Archived = null;
		
		this.GoalBalance = goalBalance;
		this.DateBy = dateBy;
		this.Salary = salary;
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
	
	public Budget getBudgetProperties() {
		return new Budget(getId(), this.GoalBalance, this.DateBy, this.Salary);
	}
	
	public void setBudgetProperties(Budget budget) {
		this.GoalBalance = budget.getGoalBalance();
		this.DateBy =  budget.getDateBalance();
		this.Salary = budget.getSalary();
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
}
