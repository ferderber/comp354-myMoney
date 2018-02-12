package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import main.java.dao.AccountDao;
import main.java.models.Account;
import main.java.views.SingleAccountView;

/**
 * Provides a list of TransactionViews.
 * 
 * @author Artem Khomich
 *
 */
public class AccountListController implements Initializable {

	@FXML
	private Button NewAccountButton;
	@FXML
	private VBox AccountListcontainer;

	private List<SingleAccountView> accountViews;
	@FXML
	private AccountController accountController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		accountViews = new ArrayList<SingleAccountView>();
		// AccountDao dao = new AccountDao();
		// Get all transactions from the database
		List<Account> accounts = this.getAccounts();
		// Create a TransactionView forEach transaction obj
		accounts.forEach((account) -> {
			accountViews.add(new SingleAccountView(account));
		});
		// Add all of the transactions to the list
		AccountListcontainer.getChildren().addAll(accountViews);
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the transactionClickHandler for each TransactionView
	 * 
	 * @param handler
	 */
	public void setOnAccountViewClick(EventHandler<MouseEvent> handler) {
		accountViews.forEach((tView) -> tView.setOnAction(handler));
	}

	@FXML
	private List<Account> getAccounts() {
		AccountDao accountDao = new AccountDao();
		return accountDao.getAllAccounts();
	}

	public void AccountAddClick(EventHandler<MouseEvent> handler) {
		NewAccountButton.setOnMouseClicked(handler);
	}

}
