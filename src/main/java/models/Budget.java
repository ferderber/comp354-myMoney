package main.java.models;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;



public class Budget {
	//Goal Setting Properties
	@DatabaseField(generatedId = true)
	private int ID;
	@DatabaseField
	private String end_goal;
	@DatabaseField
	private String goal_by_date;
	@DatabaseField
	private String salary;
	
	public Budget(){}
	
	public Budget(String end_goal, String goal_by_date, String salary){
		setFields(end_goal, goal_by_date, salary);
	}
	
	public Budget(int ID, String end_goal, String goal_by_date, String salary){
		this.ID = ID;
		setFields(end_goal, goal_by_date, salary);
	}
	
	public void setFields(String end_quantity, String goal_date, String yearly_salary){
		end_goal =  end_quantity; 
		goal_by_date = goal_date; 
		salary = yearly_salary + "";
	}
	
	public String getGoalBalance() {
		return end_goal;
	}
	public String getDateBalance() {
		return goal_by_date;
	}
	public String getSalary() {
		return salary;
	}
	
	public double calculateWeeklySpendingLimit(double current_account_balance) {
		//get days until goal to reach
		int days_to_reach = convertDateToDays();
		double amount_to_reach = Double.parseDouble(end_goal) - current_account_balance;
		double daily_savings = amount_to_reach / days_to_reach;
		double daily_salary = (Double.parseDouble(salary)) / 365.0;
		System.out.println((daily_salary + "-" + daily_savings) +"<" + daily_salary);
		if((daily_salary - daily_savings)  < 0) return -1;
		else return (daily_salary - daily_savings) * 7;
	}
	
	public int convertDateToDays() {
		int current_year = Calendar.getInstance().get(Calendar.YEAR);
		int current_month =  Calendar.getInstance().get(Calendar.MONTH) + 1;
		int current_day =  Calendar.getInstance().get(Calendar.DATE);
		
		String[] date_arr = goal_by_date.split("\\/");
		int goal_year = Integer.parseInt(date_arr[0]);
		int goal_month = Integer.parseInt(date_arr[1]);
		int goal_day = Integer.parseInt(date_arr[2]);
		
		int year_diff = goal_year - current_year;
		int month_diff = goal_month - current_month;
		

		month_diff += year_diff * 12;
		
		int days_in_current_month = (new GregorianCalendar(current_year, 
				current_month - 1, 1)).getActualMaximum(Calendar.DAY_OF_MONTH);
		int days_in_final_month = (new GregorianCalendar(goal_year, 
				goal_month - 1, 1)).getActualMaximum(Calendar.DAY_OF_MONTH);
		int day_diff = 0;
		if(month_diff == 0) {
			//	24 =			30			-			2		 +		26		-	30  
			day_diff = (days_in_current_month - current_day) + (goal_day - days_in_final_month);
		}
		else {
//			24 =			30			-			2		 -- 1st month
			day_diff = (days_in_current_month - current_day);
//			 55	 +=	24    + 31								 -- 3rd month
			day_diff += (goal_day);
			
			for(int month = 1 ; month < month_diff; month++) {
				int actual_month = (current_month - 1 + month) % 12;
				if(actual_month == 0) {
					current_year++;
				}
				days_in_current_month = (new GregorianCalendar(current_year, 
						actual_month, 1)).getActualMaximum(Calendar.DAY_OF_MONTH);
				day_diff += days_in_current_month;		
			}
		}	
		return day_diff; 
	}	
	
}
