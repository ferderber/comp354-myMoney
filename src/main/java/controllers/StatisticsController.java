package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import main.Statistics.Stats_Interface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.views.TransactionView;

public class StatisticsController implements Initializable
{
	@FXML
	private VBox container;

	@FXML
	private List<String> transactionViews;
	
	@FXML
	private Text transactionName;
	
	@FXML
	private Button updateStatisticsButton;
	
	@FXML
	private TextField transactionType;
	
	@FXML
	private TextField transactionRecc;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		transactionViews = new ArrayList<String>();
	}
	public void updateStatistics(ActionEvent event) {
		
		Stats_Interface inter = new Stats_Interface();
		
		String Temp = "";
		String type = transactionType.getText();
		String recc = transactionRecc.getText();
		int rec = 0;
		
		if(!recc.equals(""))
		{
			rec = Integer.parseInt(recc);
		}
		
		if(type.equals(""))
		{
			
			List<String> list = inter.ExtremeMonths();
			
			for(int i=0; i<list.size(); i++)
			{
				Temp += list.get(i)+"\n";
			}
		}
		else if((!type.equals("")) && recc.equals(""))
		{
			List<String> list = inter.displayStats(type);
			
			for(int i=0; i<list.size(); i++)
			{
				Temp += list.get(i)+"\n";
			}
		}
		else if((!type.equals("")) && (!recc.equals("")))
		{
			List<String> list = inter.displayRecurring(type, rec);
			
			for(int i=0; i<list.size(); i++)
			{
				Temp += list.get(i)+"\n";
			}
		}
		
		
		
		transactionName.setText(Temp);
	}
	
	
	
	
	
}
