package Statistics;

import java.util.ArrayList;
import java.util.List;

import main.java.dao.TransactionDao;
import main.java.models.Transaction;

public class Statistics 
{
	
	public List<Transaction> getAllTransactions()
	{
		TransactionDao Trans = new TransactionDao();
		
		return Trans.getAllTransactions();
	}
	
	public int getTotalMonths()
	{
		List<Transaction> list = getAllTransactions();
		
		int end = list.size()-1;
		int month = 0;
		int month2 = 0;
		int year = 0;
		int year2 = 0;
		int result = 0;
		
		month = list.get(0).getDate().getMonth();
		month2 = list.get(end).getDate().getMonth();
		
		year = list.get(0).getDate().getYear();
		year2 = list.get(end).getDate().getYear();
		
		result = (month2 - month + 1) + (year2 - year)*12;
		
		return result;
	}
	
	public List<Transaction> getMonthsTransactions(int number)
	{
		List<Transaction> list = getAllTransactions();
		List<Transaction> list2 = new ArrayList<Transaction>();
		
		int end = list.size()-1;
		int temp = list.get(end).getDate().getMonth();
		int count = 0;
		int store = 0;
		
		for(int i=end; i>=0; i--)
		{
			if(temp != list.get(i).getDate().getMonth())
			{
				temp = list.get(i).getDate().getMonth();
				count += 1;
			}
			if(count == number)
			{
				store = i-1;
				break;
			}
		}
		
		list2 = list.subList(store, end);
		
		return list2;
	}
	
	public double getTotalAverage()
	{
		List<Transaction> list = getAllTransactions();
		int end = list.size()-1;
		
		int avg = 0;
		
		for(int i=end; i>=0; i--)
		{
			avg += list.get(i).getAmount(); 
		}
		
		avg = avg/(end+1);
		return avg;
	}
	
	public double getMonthsAverage(int number)
	{
		List<Transaction> list = getMonthsTransactions(number);
		int end = list.size()-1;
		
		int avg = 0;
		
		for(int i=end; i>=0; i--)
		{
			avg += list.get(i).getAmount(); 
		}
		
		avg = avg/(end+1);
		
		return avg;
	}
	
	public double getAverageIn()
	{
		List<Transaction> list = getAllTransactions();
		int end = list.size()-1;
		
		int avg = 0;
		
		for(int i=end; i>=0; i--)
		{
			if(list.get(i).getAmount()>0)
			{
				avg += list.get(i).getAmount();
			}
		}
		
		avg = avg/(end+1);
		
		return avg;
	}
	
	public double getAverageOut()
	{
		List<Transaction> list = getAllTransactions();
		int end = list.size()-1;
		
		int avg = 0;
		
		for(int i=end; i>=0; i--)
		{
			if(list.get(i).getAmount()<0)
			{
				avg += list.get(i).getAmount();
			}
		}
		
		avg = avg/(end+1);
		
		return avg;
	}
	
	public double getAverageInMonth(int number)
	{
		List<Transaction> list = getMonthsTransactions(number);
		int end = list.size()-1;
		
		int avg = 0;
		
		for(int i=end; i>=0; i--)
		{
			if(list.get(i).getAmount()>0)
			{
				avg += list.get(i).getAmount();
			}
		}
		
		avg = avg/(end+1);
		
		return avg;
	}
	
	public double getAverageOutMonth(int number)
	{
		List<Transaction> list = getMonthsTransactions(number);
		int end = list.size()-1;
		
		int avg = 0;
		
		for(int i=end; i>=0; i--)
		{
			if(list.get(i).getAmount()<0)
			{
				avg += list.get(i).getAmount();
			}
		}
		
		avg = avg/(end+1);
		
		return avg;
	}
	
	public double getTotalMedian()
	{
		return 0;
	}
	
	public double getMonthsMedian(int number)
	{
		return 0;
	}
	
	public double getMedianIn()
	{
		return 0;
	}
	
	public double getMedianOut()
	{
		return 0;
	}
	
	public double getMedianInMonth(int number)
	{
		return 0;
	}
	
	public double getMedianOutMonth(int number)
	{
		return 0;
	}
	
	public List<Transaction> getRecurring()
	{
		return null;
	}
	
	public Transaction getMaxIn()
	{
		List<Transaction> list = getAllTransactions();
		
		double max = 0;
		int store = 0;
		
		for(int i=0; i<list.size(); i++)
		{
			if(max < list.get(i).getAmount())
			{
				max = list.get(i).getAmount();
				store = i;
			}
		}
		
		return list.get(store);
	}
	
	public Transaction getMaxOut()
	{
		List<Transaction> list = getAllTransactions();
		
		double min = 0;
		int store = 0;
		
		for(int i=0; i<list.size(); i++)
		{
			if(min > list.get(i).getAmount())
			{
				min = list.get(i).getAmount();
				store = i;
			}
		}
		
		return list.get(store);
	}
	
	public List<Transaction> getMaxRecurring()
	{
		return null;
	}
	
	
	
	public static void main(String args[])
	{
		
		Statistics stat = new Statistics();
		
		List<Transaction> list = new ArrayList<Transaction>();
		
		list = stat.getMonthsTransactions(3);
		
		/*for(int i=0; i<list.size(); i++)
		{
			System.out.println(list.get(i).toString());
			
		}*/
		
		System.out.println("Total Spending Average: $" + stat.getTotalAverage());
		System.out.println("Total Spending Average last 4 Months: $" + stat.getAverageInMonth(4));
		System.out.println("Total Inflow Average: $" + stat.getAverageIn());
		System.out.println("Total Inflow Average last 4 Months: $" + stat.getAverageInMonth(4));
		System.out.println("Total Outflow Average: $" + stat.getAverageOut());
		System.out.println("Total Outflow Average last 4 Months: $" + stat.getAverageOutMonth(4));
		System.out.println("Max Inflow TransAction: " + stat.getMaxIn().toString());
		System.out.println("Max Outflow TransAction: " + stat.getMaxOut().toString());
	}
	
	
}
