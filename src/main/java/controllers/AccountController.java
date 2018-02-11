package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Provides an Account Controller.
 * 
 * @author Artem Khomich
 *
 */
public class AccountController implements Initializable {
	@FXML
	private AccountSingleController accountSingleController;
	@FXML
	private AccountListController accountListController;
	@FXML
	private Pane accountContainer;
	@FXML
	private ScrollPane accountList;
	@FXML
	private Pane accountAdd;
	@FXML
	private FlowPane accountAddPane;
	
	@FXML
	private Button AddAccountButton;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		accountList.setVisible(false);
		accountAdd.setVisible(true);
		//accountSingleController.setOnBackButtonClick(new ViewChangeClickHandler());
		accountListController.AccountAddClick(new ViewChangeClickHandler());

	}
	@FXML
	public void switchAccountView() {
		accountList.setVisible(!accountList.isVisible());
		accountAdd.setVisible(!accountAdd.isVisible());
	}
	@FXML
	public void AccountAddClick() {
		switchAccountView();
	}
	
	public class ViewChangeClickHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			switchAccountView();
		}

	}
}
