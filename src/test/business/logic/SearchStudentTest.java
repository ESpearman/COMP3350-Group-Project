package test.business.logic;

import junit.framework.TestCase;
import lms.business.Student;
import lms.business.logic.SearchStudent;
import lms.persistence.CurrentDB;

import java.util.UUID;

public class SearchStudentTest extends TestCase {

	Student student1;
	Student morgan;
	Student ghost;
	
	protected void setUp() throws Exception
	{
		CurrentDB.init(true);
		UUID term = UUID.randomUUID();
		student1 = new Student("morgan", "epp", "mrgnepp", 1234567, true, term);
		student1.save();
	}
	
	public void testValidInput()
	{
		morgan = SearchStudent.getByStudentNumber("1234567"); //Should return valid
		assertTrue("getByStudentNumber() did not return proper first name\nexpected: Morgan\nactual:" + morgan.getFirstName(), morgan.getFirstName().equals("morgan"));
		assertTrue("getByStudentNumber() did not return proper last name", morgan.getLastName().equals("epp"));
		assertTrue("getByStudentNumber() did not return that they're a science student", morgan.isScienceStudent());
	}
	
	public void testCharacterInput()
	{
		ghost = SearchStudent.getByStudentNumber("abcdefg"); //characters with proper length
		assertNull("getByStudentNumber() did not return null on character input", ghost);
		ghost = SearchStudent.getByStudentNumber("abcd"); //characters with less than 7
		assertNull("getByStudentNumber() did not return null on character input", ghost);
	}
	
	public void testDigitInput()
	{
		//Tests that should return null as they aren't in the stubDB
		ghost = SearchStudent.getByStudentNumber("123456"); // less than 7 digits
		assertNull("getByStudentNumber() did not return null on too little digit input", ghost);
		ghost = SearchStudent.getByStudentNumber("12345678901"); //greater than 10 digits
		assertNull("getByStudentNumber() did not return null on too much digit input", ghost);
	}
	public void testEmptyInput()
	{
		ghost = SearchStudent.getByStudentNumber(""); //no student number entered
		assertNull("getByStudentNumber() did not return null for no input", ghost);
	}
}