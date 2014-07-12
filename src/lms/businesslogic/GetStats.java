package lms.businesslogic;

import java.util.ArrayList;

import lms.domainobjects.Rental;

public class GetStats 
{
	public static double getSales()
	{
		double totalSales = 0;
		ArrayList <Rental> stats = new ArrayList<Rental>();
		stats = Rental.getListByTerm(CurrentTermInfo.currentTerm.getId());
		for(int i = 0; i < stats.size(); i++)
		{
			totalSales += stats.get(i).getPricePaid();
		}
		return totalSales;
	}
}
