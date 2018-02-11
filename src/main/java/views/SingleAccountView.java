package main.java.views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import main.java.models.Account;

public class SingleAccountView extends FlowPane {

	private Account account;
	private EventHandler<MouseEvent> onAction;
	private ObjectProperty<EventHandler<MouseEvent>> propertyOnAction = new SimpleObjectProperty<EventHandler<MouseEvent>>();

	public SingleAccountView(Account account) {
		super();
		this.account = account;
		this.getChildren().add(new Text(account.getName()));
	}

	public void setOnAction(EventHandler<MouseEvent> eventHandler) {
		onAction = eventHandler;
		this.setOnMouseClicked(onAction);
	}

	public EventHandler<MouseEvent> getOnAction() {
		return propertyOnAction.get();
	}

	public Account getTransaction() {
		return account;
	}

}
