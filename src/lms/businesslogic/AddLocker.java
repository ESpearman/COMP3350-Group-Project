package lms.businesslogic;

import java.util.ArrayList;
import java.util.UUID;

import lms.domainobjects.Locker;
import lms.domainobjects.LockerSize;

public class AddLocker 
{
	public static Locker insert(UUID term, String number, UUID building, LockerSize size)
	{
		int num = Integer.parseInt(number);
		
		ArrayList<Locker> currLockers = Locker.getListByTerm(term);
		for(Locker currLocker : currLockers)
		{
			if(currLocker.getNumber()==num)
			{
				return null;
			}
		}
		
		Locker newLocker = new Locker(term, num, building, size);
		newLocker.save();
		return newLocker;
	}
}
