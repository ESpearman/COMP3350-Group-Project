package lms.business.logic;

import java.util.ArrayList;
import java.util.UUID;

import lms.business.Rental;

public class RentLocker
{
	// selectedLocker is guaranteed to not be rented. As we do not allow rented lockers to be selected
	public static Rental rent(UUID potentialRenterUUID, UUID selectedLockerUUID, UUID termUUID, float price)
	{
		ArrayList<Rental> rentalTerm = Rental.getListByTerm(termUUID);
		boolean isRenting = false;
		
		// Check current term if they're already renting a locker this term
		for(int i = 0; i < rentalTerm.size() && isRenting == false; i++)
		{
			if(rentalTerm.get(i).getStudent().equals(potentialRenterUUID))
			{
				isRenting = true;
			}
		}
		
		// If they aren't then register. Otherwise return null
		if(isRenting == false) 
		{
			Rental newRental = new Rental(termUUID, potentialRenterUUID, selectedLockerUUID, price, true);
			newRental.save();
			
			return newRental;
		}
		
		return null;
	}
}
