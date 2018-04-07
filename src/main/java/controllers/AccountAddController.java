package main.java.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import main.java.dao.AccountDao;
import main.java.models.Account;
import main.java.models.Enumerator.AccountType;

/**
 * Provides a view for single controller.
 * 
 * @author Artem Khomich
 * @author Viktoriya Malinova
 *
 */
public class AccountAddController implements Initializable {

	private AccountDao dao;
	@FXML
	private Button backToAccountListButton;

	@FXML 
	private Button saveAccountButton;

	/*
	 * @FXML private AccountController accountController;
	 */

	@FXML
	private TextField nameField;
	@FXML
	private TextField numberField;
	@FXML
	private TextField typeField;
	@FXML
	private TextField balanceField;

	@FXML 
	private GridPane singleAccountContainer;
	@FXML
	private ObjectProperty<Account> accountProperty = new SimpleObjectProperty<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new AccountDao();
	}

	@FXML
	public void saveAccount() {
		Account a = accountProperty.get();
		if (a == null) {
			a = new Account();
			a.setCreate(new Date());
			a.setEdit(null);
			a.setArchived(null);
		}

		String name = nameField.getText();
		if(name.equals(""))
			name="No Name";

		long number = 0L;
		try {
			number = Long.parseLong(numberField.getText());
		} catch (Exception ex) {}

		AccountType type = null;
		String typeName = "";
		try {
			typeName=typeField.getText();
			type = AccountType.valueOf(typeName);
		} catch (Exception ex) {
			if (typeName.equals(""))
				typeName="Undefined";
		}

		double balance = 0.0;
		try {
			balance = Double.parseDouble(balanceField.getText());
		} catch (Exception ex) {}

		a.setName(name);
		a.setBalance(balance);
		a.setNumber(number);
		a.setType(type);
		a.setTypeName(typeName);
		try {
			if (accountProperty.get() == null) {
				dao.insert(a);
			} else {
				dao.updateAccount(a);
			}
		} catch (Exception ex) {}		
		clearAccount();
	}

	public void setOnBackButtonClick(EventHandler<MouseEvent> handler) {
		backToAccountListButton.setOnMouseClicked(handler);
	}
	@FXML
	private void clearAccount() {
		nameField.setText("");
		typeField.setText("");
		balanceField.setText("");
		numberField.setText("");
	}
}
