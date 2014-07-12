package lms.businesslogic;

import java.util.ArrayList;

import lms.domainobjects.Locker;
import lms.domainobjects.Rental;
import lms.domainobjects.Student;
import lms.domainobjects.Term;

public class RentLocker
{
	// selectedLocker is guaranteed to not be rented. As we do not allow rented lockers to be selected
	public static String rent(Student potentialRenter, Locker selectedLocker, ArrayList<Term> terms, double price)
	{
		String errorMessage = "";
		ArrayList<Rental> rentalTerm;
		Locker locker;
		Student student;
		boolean lockerExists = true;
		boolean isRenting = false;
		boolean isFree = true;
		
		for(Term t : terms)
		{
			rentalTerm = Rental.getListByTerm(t.getId());
			locker = Locker.getByNumberBuildingAndTerm(selectedLocker.getNumber(), selectedLocker.getBuilding(), t.getId());
			student = Student.getByStudentNumber(potentialRenter.getStudentNumber(), t.getId());
			
			// Register student if they do not exist for that term
			student = RegisterStudent.upsertStudent(student, potentialRenter.getFirstName(), potentialRenter.getLastName(), potentialRenter.getEmail(), 
					potentialRenter.getStudentNumber(), potentialRenter.isScienceStudent(), t.getId());
			if(locker == null)
			{
				lockerExists = false;
				errorMessage += "The locker doesn't exist for term: " + t.getName() + ". Make sure to set up each term before trying to rent\n";
			}
			else
			{
				// Check current term if they're already renting a locker this term
				for(int i = 0; i < rentalTerm.size() && !isRenting && isFree; i++)
				{
					if(locker.isRented())
					{
						isFree = false;
						errorMessage += "The locker is already rented for the term: " + t.getName() + "\n";
					}
					
					if(rentalTerm.get(i).getStudent().equals(student.getId()))
					{
						isRenting = true;
						errorMessage += "The student is already renting a locker for the term: " + t.getName() + "\n";
					}
				}
			}
		}
		 
		// If they aren't then register. Otherwise return false
		if(!isRenting && lockerExists && isFree) 
		{
			for(Term t : terms)
			{
				student = Student.getByStudentNumber(potentialRenter.getStudentNumber(), t.getId());
				Locker tempLocker = Locker.getByNumberBuildingAndTerm(selectedLocker.getNumber(), selectedLocker.getBuilding(), t.getId());
				System.out.println("selected: " + selectedLocker.getNumber() + " | tempLocker: " + tempLocker.getNumber() + "\n");
				System.out.println("RENTING:\n" + t.getName() + " " + student.getStudentNumber() + " " + tempLocker.getNumber() + "\n");
				Rental newRental = new Rental(t.getId(), student.getId(), tempLocker.getId(), price, true);
				newRental.save();
			}
		}
		return errorMessage;
	}
}
