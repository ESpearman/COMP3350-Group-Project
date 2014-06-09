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
		ArrayList<Locker> currentLockers = Locker.getListByterm(currentTerm);
		ArrayList<Rental> currentRentals = Rental.getListByTerm(currentTerm);
		
		//Remove lockers that are rented
		for(int i = 0; i < currentRentals.size(); i++)
		{
			UUID locker = currentRentals.get(i).getLocker();
			currentLockers.remove(Locker.getById(locker));
		}
		
		//Remove lockers from different buildings
		for(int i = 0; i < currentLockers.size(); i++){
			if(building != currentLockers.get(i).getBuilding())
			{
				currentLockers.remove(i);
			}
		}
		
		return currentLockers;
	}
}
