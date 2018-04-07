package main.Statistics;

import java.util.List;

public class BarChart 
{
	public List<Double> MonthAvg;
	public List<Double> MonthMed;
	public List<Double> MonthIN;
	public List<Double> MonthOUT;
	public List<String> MonthNames;
	public String Year;
	public int size;
	
	
	BarChart(List<Double> MAvg, List<Double> MMed, List<Double> MIN, List<Double> MO, List<String> Names, String year)
	{
		MonthAvg = MAvg;
		MonthMed = MMed;
		MonthIN = MIN;
		MonthOUT = MO;
		MonthNames = Names;
		Year = year;
		size = MAvg.size();

	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
