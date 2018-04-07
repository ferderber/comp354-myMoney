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
		this.setId("transactionView" + transaction.getId());
		this.setOnAction(onAction);
		styleComponent("transaction-view");
		setContent();
	}

	public TransactionView(Type type) {
		super();
		this.type = type;
		styleComponent("transaction-title");
		setContentType();
	}

	private void styleComponent(String style) {
		this.getStyleClass().add(style); // add class name to component
	}

	private void setContent() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		ObservableList<Node> children = this.getChildren();
		children.add(new Text(formatter.format(transaction.getAmount())));
		children.add(new Text(transaction.getName()));

	}

	private void setContentType() {
		ObservableList<Node> children = this.getChildren();
		children.add(new Text(type.getId()));
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
