package test.businessTests.logicTests;

import java.util.ArrayList;
import java.util.UUID;

import junit.framework.TestCase;
import lms.business.Locker;
import lms.business.Term;
import lms.business.Student;
import lms.business.logic.CurrentTermInfo;
import lms.business.logic.SpreadsheetImporter;

public class SpreadsheetImporterTest extends TestCase 
{
	ArrayList<Student> students;
	ArrayList<Locker> lockers;
	
	public void setUp()
	{
		Term currentTerm = new Term(UUID.randomUUID(), "Summer 2014");
		currentTerm.save();
		
		CurrentTermInfo.id = currentTerm.getId();
		CurrentTermInfo.name = currentTerm.getName();
	}
	
	public void testBlankSheetStudent()
	{
		SpreadsheetImporter.importStudents("\\src\\test\\testFiles\\Students0.xlsx");
		students = Student.getListByTerm(CurrentTermInfo.id);
		
		assertEquals(0, students.size());
	}
	
	public void testXLSXImportStudent()
	{
		SpreadsheetImporter.importStudents("\\src\\test\\testFiles\\Students1.xlsx");
		students = Student.getListByTerm(CurrentTermInfo.id);
		
		assertEquals("Alice", students.get(0).getFirstName());
		assertEquals("Zuwatski", students.get(0).getLastName());
		assertEquals("zooman11@myumanitoba.ca", students.get(0).getEmail());
		assertEquals(7640001, students.get(0).getStudentNumber());
	}
	
	public void testXLSImportStudent()
	{
		SpreadsheetImporter.importStudents("\\src\\test\\testFiles\\Students2.xls");
		students = Student.getListByTerm(CurrentTermInfo.id);
		
		assertEquals("Isabelle", students.get(8).getFirstName());
		assertEquals("Ricardo", students.get(8).getLastName());
		assertEquals("riccy@myumanitoba.ca", students.get(8).getEmail());
		assertEquals(9999999, students.get(8).getStudentNumber());
	}
	
	public void testBlankRowsStudent()
	{
		SpreadsheetImporter.importStudents("\\src\\test\\testFiles\\Students3.xlsx");
		students = Student.getListByTerm(CurrentTermInfo.id);
		
		assertEquals("Alice", students.get(0).getFirstName());
		assertEquals("Zuwatski", students.get(0).getLastName());
		assertEquals("zooman11@myumanitoba.ca", students.get(0).getEmail());
		assertEquals(7640001, students.get(0).getStudentNumber());
	}

}
