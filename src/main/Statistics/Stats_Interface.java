package main.Statistics;


import java.util.ArrayList;
import java.util.List;

import main.java.models.Transaction;

public class Stats_Interface 
{
	
	public List<String> ExtremeMonths()
	{
		Statistics stat = new Statistics();
		
		List<String> array = new ArrayList<String>();
		
		String temp = "";
		
		List<Transaction> list = stat.getAllTransactions();
		
		double TotalAvg = stat.getTotalAverage();
		double UpperTotalAvg = TotalAvg *1.05;
		double LowerTotalAvg = TotalAvg *0.95;
		
		double TotalMed = stat.getTotalMedian();
		double UpperTotalMed = TotalMed *1.05;
		double LowerTotalMed = TotalMed *0.95;
		
		double TotalIN = stat.getAverageIn();
		double UpperTotalIN = TotalIN *1.05;
		double LowerTotalIN = TotalIN *0.95;
		
		double TotalOUT = stat.getAverageOut();
		double UpperTotalOUT = TotalOUT *1.05;
		double LowerTotalOUT = TotalOUT *0.95;
		
		Transaction transMaxIN = stat.getMaxIn();
		Transaction transMaxOUT = stat.getMaxOut();
		
		int MaxInYear = transMaxIN.getDate().getYear()+1900;
		int MaxInMonth = transMaxIN.getDate().getMonth();
		
		temp = "BIGGEST INFLOW/OUTFLOW\n";
		array.add(temp);
		temp = "";
		
		temp = "Biggest Inflow is in the month of " + toMonth(MaxInMonth) + " in " + MaxInYear+ "\n" + " Amount: $" + transMaxIN.getAmount() +"\n";
		array.add(temp);
		temp = "";
		
		int MaxOutYear = transMaxOUT.getDate().getYear()+1900;
		int MaxOutMonth = transMaxOUT.getDate().getMonth();
		
		temp = "Biggest Outflow is in the month of " + toMonth(MaxOutMonth) + " in " + MaxOutYear+ "\n"+ " Amount: $" + transMaxOUT.getAmount() +"\n";
		array.add(temp);
		temp = "";
		
		int monthsDone = 0;
		int months = stat.getTotalMonths();
		ArrayList<Integer> year = stat.getYearList();
		
		double MonthAvg = 0;
		double MonthMed = 0;
		double MonthIN = 0;
		double MonthOUT = 0;
		
		
		int Cyear = year.get(0);
		int Cmonth = 0;
		
		temp = "EXTREME EVENTS BY MONTH\n";
		array.add(temp);
		temp = "";
		
		while(months != 0)
		{
			if(monthsDone >= 12)
			{
				monthsDone = 0;
				Cmonth = 0;
				
				for(int i=0; i<year.size(); i++)
				{
					if(year.get(i)> Cyear)
					{
						Cyear = year.get(i);
					}
					else
					{
						Cyear = -1;
					}
				}
				
			}
			
			
			if(Cyear != -1)
			{
				temp = "\nMONTH: " + toMonth(Cmonth) + " YEAR: " + Cyear +"\n";
				array.add(temp);
				temp = "";
			}
			
			MonthAvg = stat.getAverageByYearAndMonth(Cmonth, Cyear);
			
			if(MonthAvg != 0)
			{
				MonthMed = stat.getMedianByYearAndMonth(Cmonth, Cyear);
				MonthIN = stat.getAverageInByYearAndMonth(Cmonth, Cyear);
				MonthOUT = stat.getAverageOutByYearAndMonth(Cmonth, Cyear);
				
				if(MonthAvg > UpperTotalAvg)
				{
					temp = "Above Avg Income:" + "\n" + " Avg: $" + TotalAvg + " Actual: $" + MonthAvg + "\n";
					array.add(temp);
					temp = "";
				}
				
				if(MonthAvg < LowerTotalAvg)
				{
					temp = "Below Avg Income:" + "\n"  + " Avg: $" + TotalAvg + " Actual: $" + MonthAvg+ "\n";
					array.add(temp);
					temp = "";
				}
				
				if(MonthMed < LowerTotalMed)
				{
					temp = "Below Median Income:" + "\n"  + " Med: $" + TotalMed + " Actual: $" + MonthMed+ "\n";
					array.add(temp);
					temp = "";
				}
				if(MonthMed > UpperTotalMed)
				{
					temp = "Above Median Income:" + "\n"  + " Med: $" + TotalMed + " Actual: $" + MonthMed+ "\n";
					array.add(temp);
					temp = "";
				}
				
				if(MonthIN < LowerTotalIN)
				{
					temp = "Inflow of Income lower than average:" + "\n"  + " IN Avg: $" + TotalIN + " Actual: $" + MonthIN+ "\n";
					array.add(temp);
					temp = "";
				}
				if(MonthIN > UpperTotalIN)
				{
					temp = "Inflow of Income higher than average:" + "\n"  + " IN Avg: $" + TotalIN + " Actual: $" + MonthIN+ "\n";
					array.add(temp);
					temp = "";
				}
				
				if(MonthOUT < LowerTotalOUT)
				{
					temp = "Outflow of Income lower than average:" + "\n"  + " OUT Avg: $" + TotalOUT + " Actual: $" + MonthOUT+ "\n";
					array.add(temp);
					temp = "";
				}
				if(MonthOUT > UpperTotalOUT)
				{
					temp = "Outflow of Income higher than average:" + "\n"  + " OUT Avg: $" + TotalOUT + " Actual: $" + MonthOUT+ "\n";
					array.add(temp);
					temp = "";
				}
			}
			
			Cmonth += 1;
			monthsDone += 1;
			months -= 1;
			
			
			
		}
		
		
		return array;
	}
	
	public List<Transaction> select(String select)
	{
		String Temp = "";
		String Temp2 = "";
		
		int trans = 0;
		int trans2 = 0;
		
		Statistics stat = new Statistics();
		
		
		if(select.charAt(0) == 'a' || select.charAt(0) == 'A')
		{
			return stat.getAllTransactions();
		}
		
		if(select.charAt(0) == '2' && select.charAt(1) == '0' && select.charAt(2) == '1')
		{
			trans = Integer.parseInt(select);
			return stat.getTransactionByYear(trans);
		}
		
		if((int)select.charAt(0) > 47 && (int)select.charAt(0) < 58)
		{
			trans = Integer.parseInt(select);
			return stat.getMonthsTransactions(trans);
		}
		
		for(int i=0; i<select.length(); i++)
		{
			if((int)select.charAt(i) == 32)
			{
				Temp = select.substring(0, i);
				Temp2 = select.substring(i+1, select.length());
				trans = Integer.parseInt(Temp2);
				trans2 = toMonth(Temp);
				
				return stat.getTransactionByMonth(trans2, trans);
			}
		}
		
		return null;
	}
	
	public List<String> displayStats(String select, String Type)
	{
		Statistics stat = new Statistics();
		
		List<Transaction> list = select(select);
		
		if(!(Type == null))
		{
			list = stat.RetrunByType(Type, list);
		}
		
		List<String> array = new ArrayList<String>();
		
		String temp = "";
		
		if(list == null)
		{
			array.add("Empty");
			return array;
		}
		if(list.isEmpty())
		{
			array.add("Empty");
			return array;
		}
		
		temp = "THE STATISTICS FOR YOUR SELECTION: " + select + "\n";
		array.add(temp);
		temp = "";
		
		double Avg = stat.getAverage(list);
		
		temp = "The Average is: " + Avg + "\n";
		array.add(temp);
		temp = "";
		
		double Med = stat.getMedian(list);
		
		temp = "The Median is: " + Med + "\n";
		array.add(temp);
		temp = "";
		
		double InAvg = stat.getAverageIn(list);
		
		temp = "The Average Inflow is: " + InAvg + "\n";
		array.add(temp);
		temp = "";
		
		double OutAvg = stat.getAverageOut(list);
		
		temp = "The Average Outflow is: " + OutAvg + "\n";
		array.add(temp);
		temp = "";
		
		double InMed = stat.getMedianIn(list);
		
		temp = "The Median Inflow is: " + InMed + "\n";
		array.add(temp);
		temp = "";
		
		double OutMed = stat.getMedianOut(list);
		
		temp = "The Median Outflow is: " + OutMed + "\n";
		array.add(temp);
		temp = "";
		
		return array;
	}
	
	public List<String> displayRecurring(String select, int times, String Type)
	{
		Statistics stat = new Statistics();
		
		List<Transaction> list = select(select);
		
		List<String> array = new ArrayList<String>();
		
		if(!(Type == null))
		{
			list = stat.RetrunByType(Type, list);
		}
		
		String temp = "";
		
		list = stat.getRecurring(list, times);
		
		if(list.isEmpty())
		{
			array.add("Empty");
			return array;
		}
		
		
		temp = "RECURRING TRANSACTIONS\n";
		array.add(temp);
		temp = "";
		
		for(int i=0; i<list.size(); i++)
		{
			temp = "Transaction Name: " + list.get(i).getName() + "\nTransaction Amount: $" + list.get(i).getAmount() + "\n";
			array.add(temp);
			temp = "";
			
		}
		
		return array;
	}
	
	public BarChart displayYearStats()
	{
		
		Statistics stat = new Statistics();
		
		List<Transaction> list = stat.getAllTransactions();
		List<String> Names = new ArrayList<String>();
		List<Integer> Years = stat.getYearList();
		List<Double> MAvg = new ArrayList<Double>();
		List<Double> MMed = new ArrayList<Double>();
		List<Double> MI = new ArrayList<Double>();
		List<Double> MO = new ArrayList<Double>();
		
		int Cyear = 0;
		int Cmonth = 0;
		
		String Time = "";
		
		if(list.size()>0)
		{
			Cyear = list.get(list.size()-1).getDate().getYear() + 1900;
			Cmonth = list.get(list.size()-1).getDate().getMonth();
		}
		
		Cmonth = Cmonth - 12;
		
		if(Cmonth < 0)
		{
			Cyear -= 1;
			Cmonth += 12;
			
			if(Cmonth == 12)
			{
				Cmonth = 0;
			}
		}
		
		
		double MonthAvg = 0;
		double MonthMed = 0;
		double MonthIN = 0;
		double MonthOUT = 0;
		
		Time = Cyear + " ";
		
		for(int i=0; i<13; i++)
		{
			MonthAvg = stat.getAverageByYearAndMonth(Cmonth, Cyear);
			MonthMed = stat.getMedianByYearAndMonth(Cmonth, Cyear);
			MonthIN = stat.getAverageInByYearAndMonth(Cmonth, Cyear);
			MonthOUT = stat.getAverageOutByYearAndMonth(Cmonth, Cyear);
			
			MAvg.add(MonthAvg);
			MMed.add(MonthMed);
			MI.add(MonthIN);
			MO.add(MonthOUT);
			Names.add(toMonth(Cmonth));
			
			Cmonth += 1;
			
			if(Cmonth > 11)
			{
				Cmonth = 0;
				Cyear += 1;
				Time += Cyear;
			}
		}
		
		BarChart bc = new BarChart(MAvg, MMed, MI, MO, Names, Time);
		
		return bc;
	}
	
	public String toMonth(int month)
	{
		switch(month)
		{
			case 0:
				return "January";
			case 1:
				return "Febuary";
			case 2:
				return "March";
			case 3:
				return "April";
			case 4:
				return "May";
			case 5:
				return "June";
			case 6:
				return "July";
			case 7:
				return "August";
			case 8:
				return "September";
			case 9:
				return "October";
			case 10:
				return "November";
			case 11:
				return "December";
				
		}
		
		return null;
	}
	
	public int toMonth(String month)
	{
		switch(month)
		{
			case "January":
				return 0;
			case "Febuary":
				return 1;
			case "March":
				return 2;
			case "April":
				return 3;
			case "May":
				return 4;
			case "June":
				return 5;
			case "July":
				return 6;
			case "August":
				return 7;
			case "September":
				return 8;
			case "October":
				return 9;
			case "November":
				return 10;
			case "December":
				return 11;
				
		}
		
		return 12;
	}
	
	
	public static void main(String args[])
	{
		Stats_Interface inter = new Stats_Interface();
		
		List<String> list = new ArrayList<String>();
		
		BarChart bc  = inter.displayYearStats();
		
		list = inter.displayStats("March", null);
		
		for(int i=0; i<bc.size; i++)
		{
			System.out.println(bc.MonthAvg.get(i));
		}
		
		System.out.println(bc.Year);
	}
}
