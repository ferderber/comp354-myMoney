package main.java.views;

import java.text.NumberFormat;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.models.Transaction;

public class TransactionView extends VBox {

	private Transaction transaction;
	private EventHandler<MouseEvent> onAction;
	private ObjectProperty<EventHandler<MouseEvent>> propertyOnAction = new SimpleObjectProperty<EventHandler<MouseEvent>>();

	public TransactionView(Transaction transaction) {
		super();
		this.transaction = transaction;
		styleComponent();
		setContent();
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
