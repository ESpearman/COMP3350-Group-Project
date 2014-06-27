package lms.business.logic;

import lms.business.Locker;
import lms.business.LockerSize;
import lms.business.Student;

public class LockerPrice
{
	
	public static double scienceFull = 20;
	public static double scienceHalf = 10;
	public static double nonScienceFull = 25;
	public static double nonScienceHalf = 15;
	
	public static double calculatePrice(Student student, Locker locker)
	{
		boolean isScience = student.isScienceStudent();
		LockerSize lockerSize = locker.getSize();
		
		double cost = 0;
		
		if(isScience)
		{
			if(lockerSize == LockerSize.FULL)
			{
				cost = scienceFull;
			}
			else
			{
				cost = scienceHalf;
			}
		}
		else
		{
			if(lockerSize == LockerSize.HALF)
			{
				cost = nonScienceHalf;
			}
			else
			{
				cost = nonScienceFull;
			}
		}
		return cost;
	}
}