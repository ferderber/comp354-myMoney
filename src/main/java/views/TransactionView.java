package main.java.views;

import java.text.NumberFormat;

import com.j256.ormlite.dao.ForeignCollection;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.models.Transaction;
import main.java.models.Type;

public class TransactionView extends VBox {

	private Transaction transaction;
	private Type type;
	private EventHandler<MouseEvent> onAction;
	private ObjectProperty<EventHandler<MouseEvent>> propertyOnAction = new SimpleObjectProperty<EventHandler<MouseEvent>>();

	public TransactionView(Transaction transaction, EventHandler<MouseEvent> onAction) {
		super();
		this.transaction = transaction;
		this.setOnAction(onAction);
		styleComponent();
		setContent();
	}
	
	public TransactionView(Type type, EventHandler<MouseEvent> onAction) {
		super();
		this.type = type;
		this.setOnAction(onAction);
		styleComponent();
		setContentByType();
	}

	private void styleComponent() {
		this.getStyleClass().add("transaction-view"); // add class name to component
	}

	private void setContent() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		ObservableList<Node> children = this.getChildren();
		children.add(new Text(formatter.format(transaction.getAmount())));
		children.add(new Text(transaction.getName()));

	}
	
	private void setContentByType() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		ObservableList<Node> children = this.getChildren();
		
		ForeignCollection<Transaction> allTrans = type.getTransactions();
		children.add(new Text(type.getId()));	// the type
		for (Transaction t : allTrans) {	// each transaction of that type
			children.add(new Text(formatter.format(t.getAmount())));
			children.add(new Text(t.getName()));
		}

	}

	public void setOnAction(EventHandler<MouseEvent> eventHandler) {
		onAction = eventHandler;
		this.setOnMouseClicked(onAction);
	}

	public EventHandler<MouseEvent> getOnAction() {
		return propertyOnAction.get();
	}

	public Transaction getTransaction() {
		return transaction;
	}

}
