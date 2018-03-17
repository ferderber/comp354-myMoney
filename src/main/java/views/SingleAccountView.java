package main.java.views;

import java.text.NumberFormat;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import main.java.models.Account;

public class SingleAccountView extends FlowPane {

	private Account account;
	private EventHandler<MouseEvent> onAction;
	private ObjectProperty<EventHandler<MouseEvent>> propertyOnAction = new SimpleObjectProperty<EventHandler<MouseEvent>>();

	public SingleAccountView(Account account,EventHandler<MouseEvent> onAction) {
		super();
		this.account = account;
		this.setOnAction(onAction);
		styleComponent();
		

		//main page- prints name and balance for ech account
		ObservableList<Node> children = this.getChildren();
		children.add(new Text("\n"+account.getName()));
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		children.add(new Text("\n  (Current Balance: "+formatter.format(account.getBalance())+")"));
	}

	public void setOnAction(EventHandler<MouseEvent> eventHandler) {
		onAction = eventHandler;
		this.setOnMouseClicked(onAction);
	}

	
	public EventHandler<MouseEvent> getOnAction() {
		return propertyOnAction.get();
	}

	public Account getAccount() {
		return account;
	}
	
	private void styleComponent() {
		this.getStyleClass().add("account-view"); // add class name to component
	}

}
