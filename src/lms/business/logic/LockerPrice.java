package lms.business.logic;

import lms.business.Locker;
import lms.business.LockerSize;
import lms.business.Student;

public class LockerPrice
{
	private static final float SCI_HALF = 10;
	private static final float NONSCI_HALF = 15;
	private static final float SCI_FULL = 20;
	private static final float NONSCI_FULL = 25;

	public static float calculatePrice(Student student, Locker locker)
	{
		boolean isScience = student.isScienceStudent();
		LockerSize lockerSize = locker.getSize();
		
		float cost = 0;

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