package test.stubdb;

import junit.framework.TestCase;
import lms.business.Building;
import lms.business.Locker;
import lms.business.LockerSize;
import lms.business.Rental;
import lms.business.Student;
import lms.business.Term;
import lms.stubdb.StubDB;

public class StubDBTest extends TestCase
{
	private Term term;
	private Student student;
	private Locker locker;
	private Building building;
	private Rental rental;
	
	protected void setUp() throws Exception
	{
		StubDB.resetDB();
		term = new Term("Summer 2014");
		student = new Student("First", "Last", "test@example.com", 123456, true, term.getId());
		building = new Building("Machray Hall");
		locker = new Locker(term.getId(), 123, building.getId(), LockerSize.FULL);
		rental = new Rental(term.getId(), student.getId(), locker.getId(), 0.0f, true);
	}
	
	public void saveAndGetStudentById()
	{
		StubDB.saveStudent(student);
		Student clone = StubDB.getStudentById(student.getId());
		assertNotNull("Unable to find saved student in the database", clone);
	}
}
