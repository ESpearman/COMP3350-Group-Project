package test.business.logic;

import junit.framework.TestCase;
import lms.business.Student;
import lms.business.logic.RegisterStudent;
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
	