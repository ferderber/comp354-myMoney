package main.java.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Type {
	
	@DatabaseField(id = true)
	private String id;
	
	@ForeignCollectionField
	private ForeignCollection<Transaction> transactions;
	
	

	protected Type() {

	}

	public Type(String type) {
		this.id = type;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		Type t = (Type) obj;
		return t != null && id.equals(t.id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	public ForeignCollection<Transaction> getTransactions() {
		return transactions;
	}
	
	public void addTransaction(Transaction trans) {
		transactions.add(trans);
	}
	
	public void removeTransaction(Transaction trans) {
		transactions.remove(trans);
	}
}
