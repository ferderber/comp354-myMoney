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
import javafx.scene.layout.GridPane;
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
	private VBox accountListContainer;
	
	@FXML
	private GridPane accountListGridPane;

	private List<SingleAccountView> accountViews;
	@FXML
	private AccountController accountController;
	
	private EventHandler<MouseEvent> accountViewActionHandler;
	@FXML
	private Button returnToMainViewButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		accountViews = new ArrayList<SingleAccountView>();
	}
	
	/* Sets the ToAccountDetailHandler for each SingleAccountView
	 * @param handler
	 */
	public void setupAccounts(EventHandler<MouseEvent> handler){
		accountViewActionHandler = handler;
		// remove all previous accountViews
		accountViews.clear();
		accountListContainer.getChildren().clear();
		List<Account> accounts = this.getAccounts(); 			// accounts from database 
		accounts.forEach((account) -> {				 			// Create a SingleAccountView forEach account
			accountViews.add(new SingleAccountView(account, accountViewActionHandler)); });
		accountListContainer.getChildren().addAll(accountViews);//add accounts to container
	}
	
	

	/**
	 * Sets the transactionClickHandler for each TransactionView
	 * 
	 * @param handler
	 */
/*	public void setOnAccountViewClick(EventHandler<MouseEvent> handler) {
		accountViews.forEach((tView) -> tView.setOnAction(handler));
	}*/

	@FXML
	private List<Account> getAccounts() {
		AccountDao accountDao = new AccountDao();
		return accountDao.getAllAccounts();
	}

	public void AccountAddClick(EventHandler<MouseEvent> handler) {
		NewAccountButton.setOnMouseClicked(handler);
	}

}
