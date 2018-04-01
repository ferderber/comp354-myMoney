package main.java.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.java.dao.AccountDao;
import main.java.dao.TransactionDao;
import main.java.dao.TypeDao;
import main.java.models.Account;
import main.java.models.Transaction;
import main.java.models.Type;

public class TransactionAddController implements Initializable {

	@FXML
	private Button addTransactionButton;
	@FXML
	private GridPane transactionAdd;
	@FXML
	private TextField nameField;
	@FXML
	private TextField typeField;
	@FXML
	private TextField amountField;
	@FXML
	private TextField descriptionField;
	@FXML
	private ComboBox<String> comboBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AccountDao dbAccount=new AccountDao();
		for (Account e: dbAccount.getAllAccounts())
			comboBox.getItems().add(e.getId()+". "+e.getName());
	}

	@FXML
	private void addTransaction() {
		TransactionDao transDao = new TransactionDao();
		TypeDao typeDao = new TypeDao();
		
		String name = nameField.getText();
		Type type = new Type(typeField.getText());
		String description = descriptionField.getText();
		double amount = 0.0;
		try {
			amount = Double.parseDouble(amountField.getText());
		} catch (Exception ex) {
			// TODO: Display validation error
		}
		//vik: retrieves & converts selected item from drop menu Account to the ID of the associated account
				int account = 1;
				
				try{
					String currentSelection= comboBox.getSelectionModel().getSelectedItem().toString();
					String ID= currentSelection.substring(0,1);
					account=Integer.parseInt(ID);
					if (account==1){
						ID=currentSelection.substring(0,2);
						account=Integer.parseInt(ID);
						}
					}
				catch(Exception e){
					
				}
		
		Transaction t = new Transaction(name, typeDao.insert(type), amount, description, new Date(),account);
		transDao.insert(t);
		
		clearTransaction();
	}

	@FXML
	private void clearTransaction() {
		nameField.setText("");
		typeField.setText("");
		descriptionField.setText("");
		amountField.setText("");
		comboBox.getSelectionModel().clearSelection();
	}

}
