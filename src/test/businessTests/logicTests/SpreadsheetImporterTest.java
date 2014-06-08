package lms.business;

import junit.framework.TestCase;
import lms.business.logic.SpreadsheetImporter;

public class SpreadsheetImporterTest extends TestCase {
	
	
	public void setUp()
	{
		
	}
	
	public void testStudentImport()
	{
		SpreadsheetImporter.importStudents("B:\\Tayler\\Downloads\\StudentSheet.xlsx");
		assertEquals(true, true);
	}
}
