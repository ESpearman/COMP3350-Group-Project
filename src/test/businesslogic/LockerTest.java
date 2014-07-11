package test.businesslogic;

import java.util.UUID;

import junit.framework.TestCase;
import lms.businesslogic.CurrentTermInfo;
import lms.domainobjects.Building;
import lms.domainobjects.Locker;
import lms.domainobjects.LockerSize;
import lms.domainobjects.Rental;
import lms.domainobjects.Student;
import lms.domainobjects.Term;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;

public class LockerTest extends TestCase 
{
	private UUID lockerID;
	private UUID buildingID;
	private UUID studentID;
	private UUID termID;

	protected void setUp()
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, true);
		Term currentTerm = new Term("Summer 2014");
		currentTerm.save();
		CurrentTermInfo.currentTerm = currentTerm;
		
		studentID = UUID.randomUUID();
		buildingID = UUID.randomUUID();
		lockerID = UUID.randomUUID();
		termID = currentTerm.getId();
		
		Building building = new Building(buildingID, "Test Building");
		building.save();
		
		Student student = new Student(studentID, "Fake", "Guy", "a@a.a", 1234567, true, termID);
		student.save();
	}
	
	public void testRented()
	{
		Locker locker = new Locker(lockerID, termID, 1, buildingID, LockerSize.FULL);
		locker.save();
		
		Rental rental = new Rental(termID, studentID, lockerID, 0, true);
		rental.save();
		
		assertTrue(locker.isRented());
	}
	
	public void testNotRented()
	{
		Locker locker = new Locker(lockerID, termID, 1, buildingID, LockerSize.FULL);
		locker.save();
		
		assertFalse(locker.isRented());
	}
}
