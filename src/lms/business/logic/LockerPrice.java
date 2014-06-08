package lms.business.logic;

import lms.business.Locker;
import lms.business.LockerSize;
import lms.business.Student;

class LockerPrice
{
	private static final double SCI_HALF = 10;
	private static final double NONSCI_HALF = 15;
	private static final double SCI_FULL = 20;
	private static final double NONSCI_FULL = 25;

	public static double calculatePrice(Student student, Locker locker)
	{
		boolean isScience = student.isScienceStudent();
		LockerSize lockerSize = locker.getSize();
		
		double cost = 0;

		if(isScience)
		{
			if(lockerSize == LockerSize.FULL)
			{
				cost = SCI_FULL;
			}
			else
			{
				cost = SCI_HALF;
			}
		}
		else
		{
			if(lockerSize == LockerSize.HALF)
			{
				cost = NONSCI_FULL ;
			}
			else
			{
				cost = NONSCI_HALF;
			}
		}
		return cost;
	}
}