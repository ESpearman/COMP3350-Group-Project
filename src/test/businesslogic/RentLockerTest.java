package test.businesslogic;

import junit.framework.TestCase;
import lms.businesslogic.RentLocker;
import lms.domainobjects.Building;
import lms.domainobjects.Locker;
import lms.domainobjects.LockerSize;
import lms.domainobjects.Rental;
import lms.domainobjects.Student;
import lms.domainobjects.Term;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;

public class RentLockerTest extends TestCase
{
	float price;
	Term term;
	Building building;
	Student validStudent;
	Student rentingStudent;
	Locker locker;
	Locker rentedLocker;
	Locker secondLocker;
	boolean validRental;
	boolean failedToRent;
	
	protected void setUp() throws Exception
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, true);
		price = 20;
		term = new Term("Test Term");
		building = new Building("Test Building");
		
		validStudent = new Student("morgan", "epp", "mrgnepp@gmail.com", 9876543, true, term.getId());
		locker = new Locker(term.getId(), 101, building.getId(), LockerSize.FULL);
		
		rentingStudent = new Student("carl", "sagan", "csagan@gmail.com", 1726354, true, term.getId());
		rentedLocker = new Locker(term.getId(), 666, building.getId(), LockerSize.FULL);
		secondLocker = new Locker(term.getId(), 567, building.getId(), LockerSize.FULL);
		
		term.save();
		building.save();
		validStudent.save();
		locker.save();
		
		rentingStudent.save();
		rentedLocker.save();
		secondLocker.save();
	}
	
	public void testValid()
	{
		validRental = RentLocker.rent(validStudent.getId(), locker.getId(), term.getId(), price);
		assertTrue("Rental achieved", validRental);
	}
	
	public void testStudentAlreadyRenting()
	{
		validRental = RentLocker.rent(rentingStudent.getId(), rentedLocker.getId(), term.getId(), price);
		failedToRent = RentLocker.rent(rentingStudent.getId(), secondLocker.getId(), term.getId(), price);
		assertFalse("failedToRent did not return null after trying to rent a second locker in the same term", failedToRent);
	}
}
