package lms.businesslogic;

import java.util.UUID;

import lms.domainobjects.Locker;
import lms.domainobjects.LockerSize;

public class AddLocker 
{
	public static Locker insert(UUID term, String number, UUID building, LockerSize size)
	{		
		int num = Integer.parseInt(number);
		Locker newLocker = new Locker(term, num, building, size);
		newLocker.save();
		return newLocker;
	}
}
