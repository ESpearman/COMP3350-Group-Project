package lms.business.logic;

import lms.business.Locker;
import lms.business.Rental;
import lms.business.logic.CurrentTermInfo;

import java.util.UUID;
import java.util.ArrayList;

public class SearchLockers
{
	public static ArrayList<Locker> getUnusedLockers(UUID building, UUID term)
	{
		UUID currentTerm = CurrentTermInfo.currentTerm.getId();
		ArrayList<Locker> currentLockers = Locker.getListByTerm(currentTerm);
		ArrayList<Rental> currentRentals = Rental.getListByTerm(currentTerm);
		ArrayList<Locker> newLockers = new ArrayList<Locker>();
		ArrayList<Locker> unusedLockers = new ArrayList<Locker>();
		
		//Remove lockers from different buildings
		for(Locker locker: currentLockers)
		{
			if(building.equals(locker.getBuilding()))
			{
				newLockers.add(locker);		
			}
		}
		
		//Remove lockers that are rented
		for(Locker locker: newLockers)
		{
			boolean flag = true;
			for(Rental rent: currentRentals)
			{			
				if(rent.getLocker().equals(locker.getId()))
				{
					flag = false;
					break;
				}
			}
			if(flag)
			{
				unusedLockers.add(locker);
			}
		}
		
		return unusedLockers;
	}
}
