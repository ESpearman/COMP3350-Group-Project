package test.businesslogic;

import junit.framework.TestCase;
import lms.businesslogic.RegisterStudent;
import lms.domainobjects.Student;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;

import java.util.UUID;

public class RegisterStudentsTest extends TestCase
{
	UUID id = UUID.randomUUID();
	UUID term = UUID.randomUUID();
	
	Student newStudent;
	Student testUpdate; 
	Student testInsert;
	Student empty; 

	public void setUp() throws Exception 
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, true);
		newStudent = new Student(id, "billal", "kohistani", "umkohisb", 1234567, true, term);
		newStudent.save();
	}
	
	public void testUpdateStudent()
	{
		testUpdate = RegisterStudent.upsertStudent(newStudent, "Morgan", "kohistani","umkohisb", 1234567, true, term);
		assertTrue("updateStudent() did not update student number", newStudent.getFirstName().equals("Morgan"));
	}
	
	public void testInsertStudent()
	{
		empty = null;
		testInsert = RegisterStudent.upsertStudent(empty, "billal", "prat", "jbrco", 0000000 , false, term);
		assertNotNull("insertStudent() did not add new student and is null", testInsert);	
	}
	
}
	