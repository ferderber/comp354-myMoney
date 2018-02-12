package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.controllers.AccountAddController;
import main.java.controllers.AccountListController;;

/**
 * Provides an Account Controller.
 * 
 * @author Artem Khomich
 *
 */
public class AccountController implements Initializable {

	@FXML
	private Pane accountAdd;
	@FXML
	private AccountAddController accountAddController;
	@FXML
	private AccountListController accountListController;
	@FXML
	private Pane accountContainer;
	@FXML
	private VBox accountList;
	/*
	 * @FXML private FlowPane accountAddPane;
	 * 
	 * @FXML private Button AddAccountButton;
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		accountList.setVisible(true);
		accountAdd.setVisible(false);
		accountListController.AccountAddClick(new ViewChangeClickHandler());
		accountAddController.setOnBackButtonClick(new ViewChangeClickHandler());
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
