package test.business.logic;

import junit.framework.TestCase;

import java.util.UUID;
import java.util.ArrayList;

import lms.business.Locker;
import lms.business.LockerSize;
import lms.business.Building;
import lms.business.Rental;
import lms.business.Student;
import lms.business.Term;
import lms.business.logic.CurrentTermInfo;
import lms.business.logic.SearchLockers;

public class SearchLockersTest extends TestCase
{
	UUID termUUID;
	UUID lockerUUID;
	UUID buildingUUID;
	UUID studentUUID;
	UUID rentalUUID;
	
	Building building;
	Locker locker;
	Rental rental;
	Student student;
	float price;
	ArrayList<Locker> unusedLockers;
	Term currTerm;
	
	protected void setUp() throws Exception
	{
		lockerUUID = UUID.randomUUID();
		buildingUUID = UUID.randomUUID();
		rentalUUID = UUID.randomUUID();
		studentUUID = UUID.randomUUID();
		price = 1;
		
		currTerm = new Term("name");
		CurrentTermInfo.currentTerm = currTerm;
		building = new Building(buildingUUID, "armes");
		locker = new Locker(lockerUUID, currTerm.getId(), 123, buildingUUID, LockerSize.FULL);
		student = new Student(studentUUID, "morgan", "epp", "mrgnepp", 1234567, true, currTerm.getId());
		
		currTerm.save();
		building.save();
		locker.save();
		student.save();
	}
	
	public void testOneUnused()
	{
		unusedLockers = SearchLockers.getUnusedLockers(buildingUUID, currTerm.getId());
		assertTrue("this is not true", unusedLockers.get(0).getNumber() == 123);
		assertFalse("this is not false", unusedLockers.get(0).getNumber() == 124);
	}
	
	public void testNoUnused()
	{
		Student student = new Student("asdf", "ghjk", "asdfghjk", 7654321, true, CurrentTermInfo.currentTerm.getId());
		Building building = new Building("evan");
		UUID newUUID1 = UUID.randomUUID();
		Locker locker2 = new Locker(newUUID1, currTerm.getId(), 666, buildingUUID, LockerSize.FULL);
		Rental rental = new Rental(currTerm.getId(), student.getId(), locker2.getId(), price, true);
		
		rental.save();
		locker2.save();
		student.save();
		building.save();
		
		unusedLockers = SearchLockers.getUnusedLockers(building.getId(), currTerm.getId());
		assertTrue(unusedLockers.size() == 0);
	}
	
	

}
