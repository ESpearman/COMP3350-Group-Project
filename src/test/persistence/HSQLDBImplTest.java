package test.persistence;

import java.util.ArrayList;
import java.util.UUID;

import junit.framework.TestCase;
import lms.config.ConfigData;
import lms.domainobjects.Building;
import lms.domainobjects.Locker;
import lms.domainobjects.LockerSize;
import lms.domainobjects.Rental;
import lms.domainobjects.Student;
import lms.domainobjects.Term;
import lms.persistence.ConnectionPool;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;

//Integration tests to check the integration between the HSQLDB database and the domain objects through the persistence layer

public class HSQLDBImplTest extends TestCase
{
	private Term term;
	private Student student;
	private Building building;
	private Locker locker;
	private Rental rental;
	
	protected void setUp() throws Exception
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, false);
		
		ConfigData.init();
		ConnectionPool.init(4);
		
		DBProxy.instance.resetDB();
		
		term = new Term("Summer 2014");
		student = new Student("First", "Last", "test@example.com", 123456, true, term.getId());
		building = new Building("Machray Hall");
		locker = new Locker(term.getId(), 123, building.getId(), LockerSize.FULL);
		rental = new Rental(term.getId(), student.getId(), locker.getId(), 0.0f, true);
		
		term.save();
		student.save();
		building.save();
		locker.save();
		rental.save();
	}
	
	public void testGetTermById()
	{
		Term clone = Term.getById(term.getId());
		assertNotNull("Unable to find saved term in the database", clone);
	}
	
	public void testGetStudentById()
	{
		Student clone = Student.getById(student.getId());
		assertNotNull("Unable to find saved student in the database", clone);
	}
	
	public void testGetBuildingById()
	{
		Building clone = Building.getById(building.getId());
		assertNotNull("Unable to find saved building in the database", clone);
	}
	
	public void testGetLockerById()
	{
		Locker clone = Locker.getById(locker.getId());
		assertNotNull("Unable to find saved locker in the database", clone);
	}
	
	public void testGetRentalById()
	{
		Rental clone = Rental.getById(rental.getId());
		assertNotNull("Unable to find saved rental in the database", clone);
	}
	
	public void testGetFakeTerm()
	{
		Term clone = Term.getById(UUID.randomUUID());
		assertNull("Found a term when we shouldn't have", clone);
	}
	
	public void testGetFakeStudent()
	{
		Student clone = Student.getById(UUID.randomUUID());
		assertNull("Found a student when we shouldn't have", clone);
	}
	
	public void testGetFakeBuilding()
	{
		Building clone = Building.getById(UUID.randomUUID());
		assertNull("Found a building when we shouldn't have", clone);
	}
	
	public void testGetFakeLocker()
	{
		Locker clone = Locker.getById(UUID.randomUUID());
		assertNull("Found a locker when we shouldn't have", clone);
	}
	
	public void testGetFakeRental()
	{
		Rental clone = Rental.getById(UUID.randomUUID());
		assertNull("Found a rental when we shouldn't have", clone);
	}
	
	public void testStudentsByTerm()
	{
		ArrayList<Student> result = Student.getListByTerm(term.getId());
		boolean testResult = result.get(0).getId().equals(student.getId());
		assertEquals("Could not find the student object based on term", testResult, true);
	}
	
	public void testNoStudentsForTerm()
	{
		ArrayList<Student> result = Student.getListByTerm(UUID.randomUUID());
		boolean testResult = result.size() == 0;
		assertEquals("Found students associated with fake term", testResult, true);
	}
	
	public void testLockersByTerm()
	{
		ArrayList<Locker> result = Locker.getListByTerm(term.getId());
		boolean testResult = result.get(0).getId().equals(locker.getId());
		assertEquals("Could not find the locker object based on term", testResult, true);
	}
	
	public void testNoLockersForTerm()
	{
		ArrayList<Locker> result = Locker.getListByTerm(UUID.randomUUID());
		boolean testResult = result.size() == 0;
		assertEquals("Found lockers associated with fake term", testResult, true);
	}
	
	public void testRentalsByTerm()
	{
		ArrayList<Rental> result = Rental.getListByTerm(term.getId());
		boolean testResult = result.get(0).getId().equals(rental.getId());
		assertEquals("Could not find the locker object based on term", testResult, true);
	}
	
	public void testNoRentalsForTerm()
	{
		ArrayList<Rental> result = Rental.getListByTerm(UUID.randomUUID());
		boolean testResult = result.size() == 0;
		assertEquals("Found rentals associated with fake term", testResult, true);
	}
	
	public void testSaveTerm()
	{
		Term newTerm = new Term("Test Term");
		newTerm.save();
		UUID termId = newTerm.getId();
		assertNotNull("Could not find saved term", Term.getById(termId));
	}
	
	public void testSaveStudent()
	{
		Student newStudent = new Student("Test", "Student", "email", 1234567, true, term.getId());
		newStudent.save();
		UUID studentId = newStudent.getId();
		assertNotNull("Could not find saved student", Student.getById(studentId));
	}
	
	public void testSaveBuilding()
	{
		Building newBuilding = new Building("new Building");
		newBuilding.save();
		UUID buildingId = newBuilding.getId();
		assertNotNull("Could not find saved building", Building.getById(buildingId));
	}
	
	public void testSaveLocker()
	{
		Locker newLocker = new Locker(term.getId(), 1, building.getId(), LockerSize.FULL);
		newLocker.save();
		UUID lockerId = newLocker.getId();
		assertNotNull("Could not find saved locker", Locker.getById(lockerId));
	}
	
	public void testSaveRental()
	{
		Rental newRental = new Rental(term.getId(), student.getId(), locker.getId(), 1f, true);
		newRental.save();
		UUID rentalId = newRental.getId();
		assertNotNull("Could not find saved rental", Rental.getById(rentalId));
	}
	
	public void testGetStudentByNumber()
	{
		Student clone = Student.getByStudentNumber(student.getStudentNumber(), term.getId());
		assertNotNull("Could not find student for number", clone);
	}
	
	public void testGetStudentByWrongNumber()
	{
		Student clone = Student.getByStudentNumber(0, UUID.randomUUID());
		assertNull("Found a student based on an invalid number", clone);
	}
	
	public void testGetLockerByNumber()
	{
		Locker clone = Locker.getByNumberBuildingAndTerm(locker.getNumber(), building.getId(), term.getId());
		assertNotNull("Could not find locker for number", clone);
	}
	
	public void testGetLockerByWrongNumber()
	{
		Locker clone = Locker.getByNumberBuildingAndTerm(0, UUID.randomUUID(), UUID.randomUUID());
		assertNull("Found a locker based on an invalid number", clone);
	}
	
	public void testGetRentalByLocker()
	{
		Rental clone = Rental.getByLocker(locker.getId());
		assertNotNull("Couldn't find a rental for the given locker", clone);
	}
	
	public void testGetRentalByStudent()
	{
		Rental clone = Rental.getByStudent(student.getId());
		assertNotNull("Couldn't find a rental for the given student", clone);
	}
	
	public void testGetRentalByFakeStudent()
	{
		Rental clone = Rental.getByStudent(UUID.randomUUID());
		assertNull("Found a rental for a fake student", clone);
	}
	
	public void testGetRentalByFakeLocker()
	{
		Rental clone = Rental.getByLocker(UUID.randomUUID());
		assertNull("Found a rental for a fake locker", clone);
	}
	
	public void testGetAllTerms()
	{
		ArrayList<Term> terms = Term.getAll();
		assertEquals("Wrong number of terms returned", terms.size(), 1);
	}
	
	public void testGetAllBuildings()
	{
		ArrayList<Building> buildings = Building.getAll();
		assertEquals("Wrong number of buildings returned", buildings.size(), 1);
	}
	
	public void tearDown()
	{
		DBProxy.instance.resetDB();
		
		ConnectionPool.clearConnections();
	}
	
}
