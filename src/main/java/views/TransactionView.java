package main.java.views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import main.java.models.Transaction;

public class TransactionView extends FlowPane {

	private Transaction transaction;
	private EventHandler<MouseEvent> onAction;
	private ObjectProperty<EventHandler<MouseEvent>> propertyOnAction = new SimpleObjectProperty<EventHandler<MouseEvent>>();

	public TransactionView(Transaction transaction) {
		super();
		this.transaction = transaction;
		this.getChildren().add(new Text(transaction.getName()));
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
