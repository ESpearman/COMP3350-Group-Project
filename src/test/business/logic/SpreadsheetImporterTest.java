package test.business.logic;

import java.util.ArrayList;

import junit.framework.TestCase;
import lms.business.Building;
import lms.business.Locker;
import lms.business.LockerSize;
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
		Term currentTerm = new Term("Summer 2014");
		currentTerm.save();
		
		CurrentTermInfo.currentTerm = currentTerm;
	}
	
	public void testBlankSheetStudent()
	{
		SpreadsheetImporter.importStudents(getPath("\\src\\test\\testFiles\\Students0.xlsx"));
		students = Student.getListByTerm(CurrentTermInfo.currentTerm.getId());
		
		assertEquals(0, students.size());
	}
	
	public void testXLSXImportStudent()
	{
		SpreadsheetImporter.importStudents(getPath("\\src\\test\\testFiles\\Students1.xlsx"));
		students = Student.getListByTerm(CurrentTermInfo.currentTerm.getId());
		
		assertEquals("Alice", students.get(0).getFirstName());
		assertEquals("Zuwatski", students.get(0).getLastName());
		assertEquals("zooman11@myumanitoba.ca", students.get(0).getEmail());
		assertEquals(7640001, students.get(0).getStudentNumber());
	}
	
	public void testXLSImportStudent()
	{
		SpreadsheetImporter.importStudents(getPath("\\src\\test\\testFiles\\Students2.xls"));
		students = Student.getListByTerm(CurrentTermInfo.currentTerm.getId());
		
		assertEquals("Isabelle", students.get(8).getFirstName());
		assertEquals("Ricardo", students.get(8).getLastName());
		assertEquals("riccy@myumanitoba.ca", students.get(8).getEmail());
		assertEquals(9999999, students.get(8).getStudentNumber());
	}
	
	public void testBlankRowsStudent()
	{
		SpreadsheetImporter.importStudents(getPath("\\src\\test\\testFiles\\Students3.xlsx"));
		students = Student.getListByTerm(CurrentTermInfo.currentTerm.getId());
		
		assertEquals("Alice", students.get(0).getFirstName());
		assertEquals("Zuwatski", students.get(0).getLastName());
		assertEquals("zooman11@myumanitoba.ca", students.get(0).getEmail());
		assertEquals(7640001, students.get(0).getStudentNumber());
	}
	
	public void testBlankSheetLocker()
	{
		SpreadsheetImporter.importLockers(getPath("\\src\\test\\testFiles\\Lockers0.xlsx"));
		lockers = Locker.getListByterm(CurrentTermInfo.currentTerm.getId());
		
		assertEquals(0, lockers.size());
	}
	
	public void testXLSXImportLocker()
	{
		SpreadsheetImporter.importLockers(getPath("\\src\\test\\testFiles\\Lockers1.xlsx"));
		lockers = Locker.getListByterm(CurrentTermInfo.currentTerm.getId());
		
		Building expectedBuilding = Building.getById(lockers.get(0).getBuilding());
		
		assertEquals("Armes", expectedBuilding.getName());
		assertEquals(1, lockers.get(0).getNumber());
		assertEquals(LockerSize.FULL, lockers.get(0).getSize());
		assertEquals(15, lockers.size());
	}
	
	public void testXLSImportLocker()
	{
		SpreadsheetImporter.importLockers(getPath("\\src\\test\\testFiles\\Lockers2.xls"));
		lockers = Locker.getListByterm(CurrentTermInfo.currentTerm.getId());
		
		Building expectedBuilding = Building.getById(lockers.get(5).getBuilding());
		
		assertEquals("Machray", expectedBuilding.getName());
		assertEquals(6, lockers.get(5).getNumber());
		assertEquals(LockerSize.HALF, lockers.get(5).getSize());
		assertEquals(15, lockers.size());
	}
	
	public void testBlankRowsLocker()
	{
		SpreadsheetImporter.importLockers(getPath("\\src\\test\\testFiles\\Lockers3.xlsx"));
		lockers = Locker.getListByterm(CurrentTermInfo.currentTerm.getId());
		
		Building expectedBuilding = Building.getById(lockers.get(14).getBuilding());
		
		assertEquals("Allen", expectedBuilding.getName());
		assertEquals(15, lockers.get(14).getNumber());
		assertEquals(LockerSize.FULL, lockers.get(14).getSize());
		assertEquals(15, lockers.size());
	}
	
	
	public String getPath(String localPath)
	{
		return System.getProperty("user.dir") + localPath;
	}

}
