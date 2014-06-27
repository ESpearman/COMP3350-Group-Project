package lms.business.logic;

import java.util.UUID;

import lms.business.Locker;
import lms.business.LockerSize;

public class AddLocker 
{
	public static Locker insert(UUID term, int number, UUID building, LockerSize size)
	{		
		Locker newLocker = new Locker(term, number, building, size);
		newLocker.save();
		return newLocker;
	}

}
