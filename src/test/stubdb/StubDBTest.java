package test.stubdb;

import java.util.ArrayList;
import java.util.UUID;

import junit.framework.TestCase;
import lms.business.Building;
import lms.business.Locker;
import lms.business.LockerSize;
import lms.business.Rental;
import lms.business.Student;
import lms.business.Term;
import lms.persistence.StubDBImpl;

public class StubDBTest extends TestCase
{
	private Term term;
	private Student student;
	private Locker locker;
	private Building building;
	private Rental rental;
	
	StubDBImpl db = new StubDBImpl();
	
	protected void setUp() throws Exception
	{
		db.resetDB();
		term = new Term("Summer 2014");
		student = new Student("First", "Last", "test@example.com", 123456, true, term.getId());
		building = new Building("Machray Hall");
		locker = new Locker(term.getId(), 123, building.getId(), LockerSize.FULL);
		rental = new Rental(term.getId(), student.getId(), locker.getId(), 0.0f, true);
		
		db.saveTerm(term);
		db.saveStudent(student);
		db.saveBuilding(building);
		db.saveLocker(locker);
		db.saveRental(rental);
	}
	
	public void testGetTermById()
	{
		Term clone = db.getTermById(term.getId());
		assertNotNull("Unable to find saved term in the database", clone);
	}
	
	public void testGetStudentById()
	{
		Student clone = db.getStudentById(student.getId());
		assertNotNull("Unable to find saved student in the database", clone);
	}
	
	public void testGetBuildingById()
	{
		Building clone = db.getBuildingById(building.getId());
		assertNotNull("Unable to find saved building in the database", clone);
	}
	
	public void testGetLockerById()
	{
		Locker clone = db.getLockerById(locker.getId());
		assertNotNull("Unable to find saved locker in the database", clone);
	}
	
	public void testGetRentalById()
	{
		Rental clone = db.getRentalById(rental.getId());
		assertNotNull("Unable to find saved rental in the database", clone);
	}
	
	public void testGetFakeTerm()
	{
		Term clone = db.getTermById(UUID.randomUUID());
		assertNull("Found a term when we shouldn't have", clone);
	}
	
	public void testGetFakeStudent()
	{
		Student clone = db.getStudentById(UUID.randomUUID());
		assertNull("Found a student when we shouldn't have", clone);
	}
	
	public void testGetFakeBuilding()
	{
		Building clone = db.getBuildingById(UUID.randomUUID());
		assertNull("Found a building when we shouldn't have", clone);
	}
	
	public void testGetFakeLocker()
	{
		Locker clone = db.getLockerById(UUID.randomUUID());
		assertNull("Found a locker when we shouldn't have", clone);
	}
	
	public void testGetFakeRental()
	{
		Rental clone = db.getRentalById(UUID.randomUUID());
		assertNull("Found a rental when we shouldn't have", clone);
	}
	
	public void testStudentsByTerm()
	{
		ArrayList<Student> result = db.getStudentsListByTerm(term.getId());
		boolean testResult = result.get(0).getId().equals(student.getId());
		assertEquals("Could not find the student object based on term", testResult, true);
	}
	
	public void testNoStudentsForTerm()
	{
		ArrayList<Student> result = db.getStudentsListByTerm(UUID.randomUUID());
		boolean testResult = result.size() == 0;
		assertEquals("Found students associated with fake term", testResult, true);
	}
	
	public void testLockersByTerm()
	{
		ArrayList<Locker> result = db.getLockersListByTerm(term.getId());
		boolean testResult = result.get(0).getId().equals(locker.getId());
		assertEquals("Could not find the locker object based on term", testResult, true);
	}
	
	public void testNoLockersForTerm()
	{
		ArrayList<Locker> result = db.getLockersListByTerm(UUID.randomUUID());
		boolean testResult = result.size() == 0;
		assertEquals("Found lockers associated with fake term", testResult, true);
	}
	
	public void testRentalsByTerm()
	{
		ArrayList<Rental> result = db.getRentalsListByTerm(term.getId());
		boolean testResult = result.get(0).getId().equals(rental.getId());
		assertEquals("Could not find the locker object based on term", testResult, true);
	}
	
	public void testNoRentalsForTerm()
	{
		ArrayList<Rental> result = db.getRentalsListByTerm(UUID.randomUUID());
		boolean testResult = result.size() == 0;
		assertEquals("Found rentals associated with fake term", testResult, true);
	}
	
	public void testGetStudentByNumber()
	{
		Student clone = db.getStudentByNumberAndTerm(student.getStudentNumber(), term.getId());
		assertNotNull("Could not find student for number", clone);
	}
	
	public void testGetStudentByWrongNumber()
	{
		Student clone = db.getStudentByNumberAndTerm(0, UUID.randomUUID());
		assertNull("Found a student based on an invalid number", clone);
	}
	
	public void testGetLockerByNumber()
	{
		Locker clone = db.getLockerByNumberBuildingAndTerm(locker.getNumber(), building.getId(), term.getId());
		assertNotNull("Could not find locker for number", clone);
	}
	
	public void testGetLockerByWrongNumber()
	{
		Locker clone = db.getLockerByNumberBuildingAndTerm(0, UUID.randomUUID(), UUID.randomUUID());
		assertNull("Found a locker based on an invalid number", clone);
	}
	
	public void testGetRentalByLocker()
	{
		Rental clone = db.getRentalByLocker(locker.getId());
		assertNotNull("Couldn't find a rental for the given locker", clone);
	}
	
	public void testGetRentalByStudent()
	{
		Rental clone = db.getRentalByStudent(student.getId());
		assertNotNull("Couldn't find a rental for the given student", clone);
	}
	
	public void testGetRentalByFakeStudent()
	{
		Rental clone = db.getRentalByStudent(UUID.randomUUID());
		assertNull("Found a rental for a fake student", clone);
	}
	
	public void testGetRentalByFakeLocker()
	{
		Rental clone = db.getRentalByLocker(UUID.randomUUID());
		assertNull("Found a rental for a fake locker", clone);
	}
	
	public void testGetAllTerms()
	{
		ArrayList<Term> terms = db.getAllTerms();
		assertEquals("Wrong number of terms returned", terms.size(), 1);
	}
	
	public void testGetAllBuildings()
	{
		ArrayList<Building> buildings = db.getAllBuildings();
		assertEquals("Wrong number of buildings returned", buildings.size(), 1);
	}
	
	
}
