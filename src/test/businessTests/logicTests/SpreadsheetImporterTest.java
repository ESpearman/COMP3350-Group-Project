package test.businessTests.logicTests;

import junit.framework.TestCase;
import lms.business.logic.SpreadsheetImporter;

public class SpreadsheetImporterTest extends TestCase 
{
	
	
	public void setUp()
	{
		
	}
	
	public void testStudentImport()
	{
		SpreadsheetImporter.importStudents("\\src\\test\\testFiles\\Students1.xlsx");
		assertEquals(true, true);
	}
}
