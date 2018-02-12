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
import main.java.dao.AccountDao;
import main.java.models.Account;
import main.java.models.Enumerator.AccountType;

/**
 * Provides a view for single controller.
 * 
 * @author Artem Khomich
 *
 */
public class AccountAddController implements Initializable {

	private AccountDao dao;
	@FXML
	private Button backToAccountListButton;
	/*
	 * @FXML private Button saveAccountButton;
	 */
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

	/*
	 * @FXML private FlowPane singleAccountContainer;
	 */
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

		long number = 0L;
		try {
			number = Long.parseLong(numberField.getText());
		} catch (Exception ex) {
			// TODO: Display validation error
		}
		AccountType type = null;
		try {
			type = AccountType.valueOf(typeField.getText());
		} catch (Exception ex) {

		}
		double balance = 0.0;
		try {
			balance = Double.parseDouble(balanceField.getText());
		} catch (Exception ex) {
			// TODO: Display validation error
		}

		a.setName(name);
		a.setBalance(balance);
		a.setNumber(number);
		a.setType(type);
		try {
			if (accountProperty.get() == null) {
				dao.insert(a);
			} else {
				dao.updateAccount(a);
			}
		} catch (Exception ex) {
			// TODO
		}
		// AccountController b = new AccountController();b.switchAccountView();
		// FXMLLoader loader = new
		// FXMLLoader(getClass().getResource("AccountView.fxml"));
		// AccountController controller = loader.getController();
		// controller.setaccountView();
		// AccountController.switchAccountView();
	}

	/*
	 * @FXML public void backToAccountList() {
	 * //AccountController.switchAccountView();
	 * 
	 * } public ObjectProperty<Account> accountProperty() { return accountProperty;
	 * }
	 * 
	 * public Account getAccount() { return accountProperty.get(); }
	 */
	/**
	 * Sets the transactionClickHandler for each TransactionView
	 * 
	 * @param handler
	 */
	public void setOnBackButtonClick(EventHandler<MouseEvent> handler) {
		backToAccountListButton.setOnMouseClicked(handler);
	}

}
