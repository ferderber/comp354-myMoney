package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.java.models.Account;

public class AccountInfoController implements Initializable{
	@FXML
	private GridPane content_container;
	@FXML
	private Account currentAccount;
	@FXML
	private ResourceBundle resources;
	@FXML
	private URL loc;
	@FXML
	private Text accountName;
	@FXML
	private Text accountTotal;
	public AccountInfoController() {
		
	}
	public AccountInfoController(Account acc) {
		currentAccount = acc;
		content_container.setVisible(true);
		setContent();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		GridPane.setHalignment(accountName, HPos.CENTER);//Align these specific text nodes in center of grid cells when contained by any gridpane.
		GridPane.setHalignment(accountTotal, HPos.CENTER);//Note that this is static, so it applies for all gridpanes.
		//content_container.setGridLinesVisible(true);//debug
		setContent();
		
	}
	protected void setCurrentAccount(Account accountToSet){
		this.currentAccount = accountToSet;
		setContent();
	}

	public void setContent() {

		if(currentAccount == null) {
			accountName.setText("No account loaded.");
			accountTotal.setText("$0.00");
		}
		else {
			accountName.setText(currentAccount.getName());
			accountTotal.setText(Account.currencyFormat(currentAccount.getTotal()) );
		}
		accountName.setTextAlignment(TextAlignment.CENTER);
		accountTotal.setTextAlignment(TextAlignment.CENTER);

	}

}
