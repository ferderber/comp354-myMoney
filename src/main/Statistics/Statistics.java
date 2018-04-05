package main.Statistics;

import java.util.ArrayList;
import java.util.List;

import main.java.dao.TransactionDao;
import main.java.models.Transaction;

public class Statistics {

	public int getTotalMonths(List<Transaction> list) {

		int end = list.size() - 1;
		int month = 0;
		int month2 = 0;
		int year = 0;
		int year2 = 0;
		int result = 0;

		month = list.get(0).getDate().getMonth();
		month2 = list.get(end).getDate().getMonth();

		year = list.get(0).getDate().getYear();
		year2 = list.get(end).getDate().getYear();

		result = (month2 - month + 1) + (year2 - year) * 12;
		System.out.println(result);
		return result;
	}

	public ArrayList<Integer> getYearList(List<Transaction> list) {

		ArrayList<Integer> array = new ArrayList<Integer>();

		if (list.size() > 0) {
			int end = list.size();
			int currentYear = list.get(0).getDate().getYear();

			for (int i = 0; i < end; i++) {
				if (currentYear != list.get(i).getDate().getYear()) {
					array.add(currentYear + 1900);

					currentYear = list.get(i).getDate().getYear();
				} else if (i == end) {
					array.add(currentYear + 1900);
				}

			}

			if (array.size() == 0) {
				array.add(currentYear + 1900);
			}
		}
		return array;
	}

	public List<Transaction> getMonthsTransactions(List<Transaction> list, int number) {
		List<Transaction> list2 = new ArrayList<Transaction>();

		if (list.size() > 0) {
			int end = list.size() - 1;
			int temp = list.get(end).getDate().getMonth();

			int count = 0;
			int store = 0;

			for (int i = end; i >= 0; i--) {
				if (temp != list.get(i).getDate().getMonth()) {
					if (temp - list.get(i).getDate().getMonth() > 1) {
						count += temp - list.get(i).getDate().getMonth();
						temp = list.get(i).getDate().getMonth();
					} else if (temp - list.get(i).getDate().getMonth() < 0) {
						count += temp - list.get(i).getDate().getMonth() + 12;
						temp = list.get(i).getDate().getMonth();
					} else {
						temp = list.get(i).getDate().getMonth();
						count += 1;
					}
				}

				if (count == number) {
					store = i + 1;
					break;
				}
			}

			if (store != end) {
				list2 = list.subList(store, end);
			} else {
				list2.add(list.get(end));
			}

		}
		return list2;
	}

	public List<Transaction> getTransactionByYear(List<Transaction> list, int year) {
		List<Transaction> list2 = new ArrayList<Transaction>();

		if (list.size() > 0) {
			int end = list.size();
			int temp = 0;

			year = year - 1900;
			for (int i = 0; i <= end; i++) {
				temp = list.get(i).getDate().getYear();

				if (temp == year) {
					list2.add(list.get(i));
				}

				if (list2.size() > 0 && temp != year) {
					return list2;
				}

				if (list.size() > 0 && i == end - 1) {
					return list2;
				}
			}

		}
		return list2;
	}

	public List<Transaction> getTransactionByMonth(List<Transaction> list, int month, int year) {
		List<Transaction> list2 = new ArrayList<Transaction>();

		int end = list.size();
		int temp = 0;

		for (int i = 0; i < end; i++) {
			temp = list.get(i).getDate().getMonth();

			if (temp == month) {
				list2.add(list.get(i));
			}

			if (list2.size() > 0 && temp != month) {
				return list2;
			}
		}

		return list2;
	}

	public double getAverage(List<Transaction> list) {
		int end = list.size();

		int avg = 0;

		for (int i = end - 1; i >= 0; i--) {
			avg += list.get(i).getAmount();
		}

		avg = avg / (end);
		return avg;
	}

	public double getTotalAverage(List<Transaction> list) {
		int end = list.size();

		int avg = 0;
		if (list.size() > 0) {
			for (int i = end - 1; i >= 0; i--) {
				avg += list.get(i).getAmount();
			}

			avg = avg / (end);
		}
		return avg;
	}

	public double getMonthsAverage(List<Transaction> allTransactions, int number) {
		List<Transaction> list = getMonthsTransactions(allTransactions, number);
		int end = list.size();

		int avg = 0;

		for (int i = end - 1; i >= 0; i--) {
			avg += list.get(i).getAmount();
		}

		avg = avg / (end);

		return avg;
	}

	public double getAverageIn(List<Transaction> list) {
		int end = list.size();

		int avg = 0;

		for (int i = end - 1; i >= 0; i--) {
			if (list.get(i).getAmount() > 0) {
				avg += list.get(i).getAmount();
			}
		}

		avg = avg / (end);

		return avg;
	}

	public double getAverageOut(List<Transaction> list) {
		int end = list.size();

		int avg = 0;

		for (int i = end - 1; i >= 0; i--) {
			if (list.get(i).getAmount() < 0) {
				avg += list.get(i).getAmount();
			}
		}

		avg = avg / (end);

		return avg;
	}

	public double getAverageInMonth(List<Transaction> list, int number) {
		int end = list.size();

		int avg = 0;
		if (list.size() > 0) {
			for (int i = end - 1; i >= 0; i--) {
				if (list.get(i).getAmount() > 0) {
					avg += list.get(i).getAmount();
				}
			}

			avg = avg / (end);
		}
		return avg;
	}

	public double getAverageOutMonth(List<Transaction> list, int number) {
		int end = list.size();

		int avg = 0;
		if (list.size() > 0) {
			for (int i = end - 1; i >= 0; i--) {
				if (list.get(i).getAmount() < 0) {
					avg += list.get(i).getAmount();
				}
			}

			avg = avg / (end);
		}
		return avg;
	}

	public int getAverageByYearAndMonth(List<Transaction> allTransactions, int month, int year) {

		List<Transaction> list = new ArrayList<Transaction>();

		list = getTransactionByMonth(allTransactions, month, year);

		System.out.println(list.size());

		int end = list.size();

		if (end <= 0) {
			return 0;
		}

		int avg = 0;

		for (int i = end - 1; i >= 0; i--) {
			avg += list.get(i).getAmount();
		}

		avg = avg / (end);

		return avg;
	}

	public int getAverageOutByYearAndMonth(List<Transaction> allTransactions, int month, int year) {

		List<Transaction> list = new ArrayList<Transaction>();

		list = getTransactionByMonth(allTransactions, month, year);

		System.out.println(list.size());

		if (list.size() == 0) {
			return 0;
		}

		int end = list.size();

		int avg = 0;

		for (int i = end - 1; i >= 0; i--) {
			if (list.get(i).getAmount() < 0) {
				avg += list.get(i).getAmount();
			}
		}

		avg = avg / (end + 1);

		return avg;
	}

	public int getAverageInByYearAndMonth(List<Transaction> allTransactions, int month, int year) {

		List<Transaction> list = new ArrayList<Transaction>();

		list = getTransactionByMonth(allTransactions, month, year);

		if (list.size() == 0) {
			return 0;
		}

		System.out.println(list.size());

		int end = list.size();

		int avg = 0;

		for (int i = end - 1; i >= 0; i--) {
			if (list.get(i).getAmount() > 0) {
				avg += list.get(i).getAmount();
			}
		}

		avg = avg / (end);

		return avg;
	}

	public List<Transaction> OrderForMedian(List<Transaction> Trans) {
		List<Transaction> list = Trans;

		int end = list.size() - 1;

		Transaction swap = null;

		for (int i = 0; i < end; i++) {
			for (int j = 0; j < end; j++) {
				if (list.get(i).getAmount() < list.get(j).getAmount()) {
					swap = list.get(i);
					list.remove(i);
					list.add(i, list.get(j));
					list.remove(j);
					list.add(j, swap);
				}

			}
		}

		return list;
	}

	public double getMaxOccurence(List<Transaction> list) {
		int end = list.size();
		int count = 0;
		int maxcount = 0;
		int position = 0;

		if (list.size() == 0) {
			return 0;
		}

		double current = list.get(0).getAmount();

		for (int i = 0; i < end; i++) {
			if (list.get(i).getAmount() == current) {
				count += 1;
			} else {
				current = list.get(i).getAmount();

				if (count > maxcount) {
					maxcount = count;
					count = 0;
					position = i - 1;
				} else {
					count = 0;
				}
			}
		}

		return list.get(position).getAmount();
	}

	public double getMedian(List<Transaction> list) {
		double median = 0;

		list = OrderForMedian(list);

		median = getMaxOccurence(list);

		return median;
	}

	public double getTotalMedian(List<Transaction> list) {
		double median = 0;

		list = OrderForMedian(list);

		median = getMaxOccurence(list);

		return median;
	}

	public double getMonthsMedian(List<Transaction> allTransactions, int number) {
		double median = 0;

		List<Transaction> list = getMonthsTransactions(allTransactions, number);

		list = OrderForMedian(list);

		median = getMaxOccurence(list);

		return median;
	}

	public double getMedianByYearAndMonth(List<Transaction> allTransactions, int month, int year) {
		double median = 0;

		List<Transaction> list = this.getTransactionByMonth(allTransactions, month, year);

		list = OrderForMedian(list);

		median = getMaxOccurence(list);

		return median;
	}

	public double getMedianIn(List<Transaction> list) {
		double median = 0;

		List<Transaction> list2 = new ArrayList<Transaction>();

		int end = list.size() - 1;

		for (int i = 0; i <= end; i++) {
			if (list.get(i).getAmount() > 0) {
				list2.add(list.get(i));
			}
		}

		if (list2.size() > 0) {
			list2 = OrderForMedian(list2);
		} else {
			return 0;
		}

		median = getMaxOccurence(list2);

		return median;
	}

	public double getMedianOut(List<Transaction> list) {
		double median = 0;

		List<Transaction> list2 = new ArrayList<Transaction>();

		int end = list.size() - 1;

		for (int i = 0; i <= end; i++) {
			if (list.get(i).getAmount() < 0) {
				list2.add(list.get(i));
			}
		}

		if (list2.size() > 0) {
			list2 = OrderForMedian(list2);
		} else {
			return 0;
		}

		median = getMaxOccurence(list2);

		return median;
	}

	public double getMedianInMonth(List<Transaction> allTransactions, int number) {
		double median = 0;

		List<Transaction> list = getMonthsTransactions(allTransactions, number);
		List<Transaction> list2 = new ArrayList<Transaction>();

		int end = list.size();

		for (int i = 0; i <= end; i++) {
			if (list.get(i).getAmount() > 0) {
				list2.add(list.get(i));
			}
		}

		if (list2.size() > 0) {
			list2 = OrderForMedian(list2);
		} else {
			return 0;
		}

		median = getMaxOccurence(list2);

		return median;
	}

	public double getMedianOutMonth(List<Transaction> allTransactions, int number) {
		double median = 0;

		List<Transaction> list = getMonthsTransactions(allTransactions, number);
		List<Transaction> list2 = new ArrayList<Transaction>();

		int end = list.size();

		for (int i = 0; i <= end; i++) {
			if (list.get(i).getAmount() < 0) {
				list2.add(list.get(i));
			}
		}

		if (list2.size() > 0) {
			list2 = OrderForMedian(list2);
		} else {
			return 0;
		}

		median = getMaxOccurence(list2);

		return median;
	}

	public Transaction getMaxIn(List<Transaction> list) {

		double max = 0;
		int store = 0;

		for (int i = 0; i < list.size(); i++) {
			if (max < list.get(i).getAmount()) {
				max = list.get(i).getAmount();
				store = i;
			}
		}
		if (list.size() > 0) {
			return list.get(store);
		} else {
			return null;
		}
	}

	public Transaction getMaxOut(List<Transaction> list) {
		double min = 0;
		int store = 0;

		for (int i = 0; i < list.size(); i++) {
			if (min > list.get(i).getAmount()) {
				min = list.get(i).getAmount();
				store = i;
			}
		}

		return list.get(store);
	}

	public Transaction getMaxInByYear(List<Transaction> allTransactions, int year) {
		List<Transaction> list = this.getTransactionByYear(allTransactions, year);

		double max = 0;
		int store = 0;

		for (int i = 0; i < list.size(); i++) {
			if (max < list.get(i).getAmount()) {
				max = list.get(i).getAmount();
				store = i;
			}
		}

		return list.get(store);
	}

	public Transaction getMaxOutByYear(List<Transaction> allTransactions, int year) {
		List<Transaction> list = this.getTransactionByYear(allTransactions, year);

		double min = 0;
		int store = 0;

		for (int i = 0; i < list.size(); i++) {
			if (min > list.get(i).getAmount()) {
				min = list.get(i).getAmount();
				store = i;
			}
		}

		return list.get(store);
	}

	public Transaction getMaxInByMonth(List<Transaction> allTransactions, int month, int year) {
		List<Transaction> list = getTransactionByMonth(allTransactions, month, year);

		double max = 0;
		int store = 0;

		for (int i = 0; i < list.size(); i++) {
			if (max < list.get(i).getAmount()) {
				max = list.get(i).getAmount();
				store = i;
			}
		}

		return list.get(store);
	}

	public Transaction getMaxOutByYear(List<Transaction> allTransactions, int month, int year) {
		List<Transaction> list = getTransactionByMonth(allTransactions, month, year);

		double min = 0;
		int store = 0;

		for (int i = 0; i < list.size(); i++) {
			if (min > list.get(i).getAmount()) {
				min = list.get(i).getAmount();
				store = i;
			}
		}

		return list.get(store);
	}

	public List<Transaction> getRecurring(List<Transaction> list, int times) {
		List<Transaction> list2 = new ArrayList<Transaction>();

		list = OrderForMedian(list);

		int end = list.size();
		int count = 1;
		double current = list.get(0).getAmount();

		for (int i = 0; i < end; i++) {
			if (current == list.get(i).getAmount()) {
				count += 1;
			} else {

				current = list.get(i).getAmount();

				if (count >= times) {

					list2.add(list.get(i - 1));
					count = 1;
				} else {
					count = 1;
				}
			}
		}

		end = list2.size();
		Transaction swap = null;

		for (int i = 0; i < end - 1; i++) {
			for (int j = 0; j < end - 1; j++) {
				if (list2.get(i).getDate().getMonth() < list2.get(j).getDate().getMonth()) {
					swap = list2.get(i);
					list2.remove(i);
					list2.add(i, list2.get(j));
					list2.remove(j);
					list2.add(j, swap);
				} else if (list2.get(i).getDate().getMonth() == list2.get(j).getDate().getMonth()) {
					if (list2.get(i).getDate().getDay() < list2.get(j).getDate().getDay()) {
						swap = list2.get(i);
						list2.remove(i);
						list2.add(i, list2.get(j));
						list2.remove(j);
						list2.add(j, swap);

					}
				}
			}
		}

		return list2;
	}

	public List<Transaction> getRecurringYear(List<Transaction> allTransactions, int times, int year) {
		List<Transaction> list = this.getTransactionByYear(allTransactions, year);
		List<Transaction> list2 = new ArrayList<Transaction>();

		list = OrderForMedian(list);

		int end = list.size();
		int count = 1;
		double current = list.get(0).getAmount();

		for (int i = 0; i < end; i++) {
			if (current == list.get(i).getAmount()) {
				count += 1;
			} else {

				current = list.get(i).getAmount();

				if (count >= times) {
					list2.add(list.get(i - 1));
					count = 1;
				} else {
					count = 1;
				}
			}
		}

		return list2;
	}

	public List<Transaction> getRecurringMonth(List<Transaction> allTransactions, int times, int month, int year) {
		List<Transaction> list = this.getTransactionByMonth(allTransactions, month, year);
		List<Transaction> list2 = new ArrayList<Transaction>();

		list = OrderForMedian(list);

		int end = list.size();
		int count = 1;
		double current = list.get(0).getAmount();

		for (int i = 0; i < end; i++) {
			if (current == list.get(i).getAmount()) {
				count += 1;
			} else {

				current = list.get(i).getAmount();

				if (count >= times) {
					list2.add(list.get(i - 1));
					count = 1;
				} else {
					count = 1;
				}
			}
		}

		return list2;
	}

	public List<Transaction> RetrunByType(String Type, List<Transaction> Passed) {
		List<Transaction> New = new ArrayList<Transaction>();

		for (int i = 0; i < Passed.size(); i++) {
			if (Passed.get(i).getType().getId().equals(Type)) {
				New.add(Passed.get(i));
			}
		}

		return New;
	}

	public List<String> returnTypes(List<Transaction> allTransactions) {
		List<String> New = new ArrayList<String>();

		for (int i = 0; i < allTransactions.size(); i++) {
			if ((!New.contains(allTransactions.get(i).getType().getId()))) {
				New.add(allTransactions.get(i).getType().getId());

			}
		}
		return New;
	}

	public static void main(String args[]) {

		Statistics stat = new Statistics();

		List<Transaction> list = new ArrayList<Transaction>();

		TransactionDao dao = new TransactionDao();

		List<Transaction> fullTransactionList = dao.getAllTransactions();

		list = stat.getTransactionByMonth(fullTransactionList, 0, 2017);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());

		}

		System.out.println("Total Spending Average: $" + stat.getTotalAverage(fullTransactionList));
		System.out.println("Total Spending Average last 4 Months: $" + stat.getAverageInMonth(fullTransactionList, 4));
		System.out.println("Total Inflow Average: $" + stat.getAverageIn(fullTransactionList));
		System.out.println("Total Inflow Average last 4 Months: $" + stat.getAverageInMonth(fullTransactionList, 4));
		System.out.println("Total Outflow Average: $" + stat.getAverageOut(fullTransactionList));
		System.out.println("Total Outflow Average last 4 Months: $" + stat.getAverageOutMonth(fullTransactionList, 4));

		System.out.println("Max Inflow TransAction: " + stat.getMaxIn(fullTransactionList).toString());
		System.out.println("Max Outflow TransAction: " + stat.getMaxOut(fullTransactionList).toString());

		System.out.println(stat.getTransactionByYear(fullTransactionList, 4));

		ArrayList<Integer> array = new ArrayList<Integer>();

		array = stat.getYearList(fullTransactionList);

		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));
		}

		System.out.println(stat.getAverageByYearAndMonth(fullTransactionList, 10, 2017));
		System.out.println(stat.getAverageInByYearAndMonth(fullTransactionList, 10, 2017));
		System.out.println(stat.getAverageOutByYearAndMonth(fullTransactionList, 10, 2017));
		System.out.println(stat.getTotalMedian(fullTransactionList));

		List<Transaction> list2 = new ArrayList<Transaction>();
		list2 = stat.OrderForMedian(fullTransactionList);

		list = stat.getMonthsTransactions(fullTransactionList, 3);
		System.out.println("size " + list.size());
		// list = stat.getRecurring(2);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getAmount());
		}

		List<String> types = new ArrayList<String>();

		types = stat.returnTypes(fullTransactionList);

		for (int i = 0; i < types.size(); i++) {
			System.out.println(types.get(i));
		}

	}

}
