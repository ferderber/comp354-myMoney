package main.Statistics;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.j256.ormlite.field.DatabaseField;

import main.java.dao.TransactionDao;
import main.java.models.Transaction;
import main.java.models.Type;

public class TransactionListCreator 
{
	
	public static void main(String args[])
	{
		TransactionDao dao = new TransactionDao();
		List<Transaction> list = new ArrayList<Transaction>();
		
		list = dao.getAllTransactions();
		
		Transaction[] arr = new Transaction[100];
		Random rand = new Random();
		
		int id = 0;
		String name = "Sample Transaction";
		double amount = 0;
		String description = "Sample Description";
		
		int year = 0;
		int month = 0;
		int day = 0;
		Type type = new Type("Sample Type");
		Date date = null;
		Transaction trans = null;
		
		for(int i=0; i<100; i++)
		{
			id = i;
			
			amount = rand.nextInt(6000) -2000;
			
			year = 117;
			month = rand.nextInt(12);
			day = rand.nextInt(30);
			
			date = new Date(year, month, day, 12, 0, 0);
			
			trans = new Transaction(name+i, type, amount, description, date);
			
			arr[i] = trans;
		}
		
		Transaction swap = null;
		
		for(int i=0; i<100; i++)
		{
			for(int j=0; j<100; j++)
			{
				if(arr[i].getDate().getMonth()<arr[j].getDate().getMonth())
				{
					swap = arr[i];
					arr[i] = arr[j];
					arr[j] = swap;
				}
				else if(arr[i].getDate().getMonth()==arr[j].getDate().getMonth())
				{
					if(arr[i].getDate().getDay()<arr[j].getDate().getDay())
					{
						swap = arr[i];
						arr[i] = arr[j];
						arr[j] = swap;
					}
				}
			}
		}
		
		for(int i=0; i<100; i++)
		{
			//System.out.println(arr[i].toString());
		}
		
		for(int i=0; i<list.size(); i++)
		{
			//dao.delete(list.get(i));
		}
		dao.insert(arr);
	}
}
