package main.Statistics;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.j256.ormlite.field.DatabaseField;

import main.java.dao.TransactionDao;
import main.java.dao.TypeDao;
import main.java.models.Transaction;
import main.java.models.Type;

public class TransactionListCreator 
{
	public void delete()
	{
		TransactionDao dao = new TransactionDao();
		List<Transaction> list = dao.getAllTransactions();
		
		for(int i=0; i<list.size()-1; i++)
		{
			dao.delete(list.get(i));
		}
		
		Type type = new Type("Sample Type");
		
		TypeDao tydao = new TypeDao();
		
		type = new Type("Sample Type");
		tydao.delete(type);
		type = new Type("Pinapple Type");
		tydao.delete(type);
		type = new Type("Lemon Type");
		tydao.delete(type);
		type = new Type("Apple Type");
		tydao.delete(type);
		type = new Type("Chicken Type");
		tydao.delete(type);
		type = new Type("Watermelon Type");
		tydao.delete(type);
		
	}
	
	public void insert()
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
		
		TypeDao tydao = new TypeDao();
		
		type = new Type("Sample Type");
		tydao.insert(type);
		type = new Type("Pinapple Type");
		tydao.insert(type);
		type = new Type("Lemon Type");
		tydao.insert(type);
		type = new Type("Apple Type");
		tydao.insert(type);
		type = new Type("Chicken Type");
		tydao.insert(type);
		type = new Type("Watermelon Type");
		tydao.insert(type);
		
		for(int i=0; i<100; i++)
		{
			id = i;
			
			if(i < 15)
			{
				type = new Type("Sample Type");
			}
			else if(i > 15 && i < 30)
			{
				type = new Type("Pinapple Type");
			}
			else if(i > 30 && i < 45)
			{
				type = new Type("Lemon Type");
			}
			else if(i > 45 && i < 60)
			{
				type = new Type("Apple Type");
			}
			else if(i > 60 && i <80)
			{
				type = new Type("Chicken Type");
			}
			else
			{
				type = new Type("Watermelon Type");
			}
			
			amount = rand.nextInt(6000) -2000;
			
			year = 117;
			month = rand.nextInt(12);
			day = rand.nextInt(30);
			
			date = new Date(year, month, day, 12, 0, 0);
			
			System.out.println(type.getId());
			
			trans = new Transaction(name+i, type, amount, description, date,1);
			
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
		
		
		dao.insert(arr);
	}
	
	public static void main(String args[])
	{
		TransactionListCreator ls = new TransactionListCreator();
		
		ls.insert();
		
	}
}
