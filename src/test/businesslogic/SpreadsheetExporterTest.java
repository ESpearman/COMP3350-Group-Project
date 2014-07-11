package test.businesslogic;

import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import junit.framework.TestCase;
import lms.businesslogic.CurrentTermInfo;
import lms.businesslogic.SpreadsheetExporter;
import lms.domainobjects.Building;
import lms.domainobjects.Locker;
import lms.domainobjects.LockerSize;
import lms.domainobjects.Rental;
import lms.domainobjects.Student;
import lms.domainobjects.Term;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;

public class SpreadsheetExporterTest extends TestCase 
{
	private Building building;
	private Term currentTerm;
	private Student student;
	private Locker locker1;
	private Locker locker2;
	private UUID buildingID;
	
	protected void setUp()
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, true);
		
		currentTerm = new Term("Summer 2014");
		currentTerm.save();
		CurrentTermInfo.currentTerm = currentTerm;
	}
	
	public void testNullInit()
	{
		XSSFSheet sheet = null;
		int[] test1 = {1, 2};
		String[] test2 = {"", ""};
		
		SpreadsheetExporter.initSheet(sheet, "test", test1, test2);
		assertNull(sheet);
	}
	
	public void testTypicalInit()
	{
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		
		int[] colOrder = {1, 2};
		String[] colTitles = {"test 1", "test2"};
		
		SpreadsheetExporter.initSheet(sheet, "test", colOrder, colTitles);
		
		assertNotNull(sheet);
		assertEquals(1, sheet.getLastRowNum());
		assertEquals(colOrder.length + 1, sheet.getRow(sheet.getLastRowNum()).getLastCellNum());
	}
	
	public void testNoRentals()
	{
		XSSFWorkbook wb = SpreadsheetExporter.createRentalList();
		assertNotNull(wb);
		assertEquals(1, wb.getSheetAt(0).getLastRowNum());
	}
	
	public void testTypicalRental()
	{
		student = new Student("asd", "asd", "asd@asd.asd", 1234567, false, currentTerm.getId());
		student.save();
		
		buildingID = UUID.randomUUID();
		building = new Building(buildingID, "Armes");
		building.save();
		
		locker1 = new Locker(currentTerm.getId(), 1, buildingID, LockerSize.FULL);
		locker1.save();
				
		Rental rental = new Rental(currentTerm.getId(), student.getId(), locker1.getId(), 0, true);
		rental.save();
		
		XSSFWorkbook wb = SpreadsheetExporter.createRentalList();
		
		assertEquals(2, wb.getSheetAt(0).getLastRowNum());
		assertEquals(1234567, (int)wb.getSheetAt(0).getRow(2).getCell(0).getNumericCellValue());
		
	}
	
	public void testNoStudents()
	{
		XSSFWorkbook wb = SpreadsheetExporter.createStudentList();
		assertNotNull(wb);
		assertEquals(1, wb.getSheetAt(0).getLastRowNum());
	}
	
	public void testTypicalStudent()
	{
		student = new Student("Tayler", "asd", "asd@asd.asd", 1234567, false, currentTerm.getId());
		student.save();
		
		XSSFWorkbook wb = SpreadsheetExporter.createStudentList();
		
		assertEquals("Tayler", wb.getSheetAt(0).getRow(2).getCell(1).getStringCellValue());
	}
	
	public void testNoLockers()
	{
		buildingID = UUID.randomUUID();
		building = new Building(buildingID, "Armes");
		building.save();
		
		XSSFWorkbook wb = SpreadsheetExporter.createLockerList(building.getId());
		assertNotNull(wb);
		assertEquals(1, wb.getSheetAt(0).getLastRowNum());
	}
	
	public void testTypicalLocker()
	{
		buildingID = UUID.randomUUID();
		building = new Building(buildingID, "Armes");
		building.save();
		
		locker1 = new Locker(currentTerm.getId(), 1, buildingID, LockerSize.FULL);
		locker2 = new Locker(currentTerm.getId(), 2, buildingID, LockerSize.HALF);
		
		locker1.save();
		locker2.save();
		
		XSSFWorkbook wb = SpreadsheetExporter.createLockerList(buildingID);
		
		assertEquals(3, wb.getSheetAt(0).getLastRowNum());
		assertEquals(1, (int)wb.getSheetAt(0).getRow(2).getCell(0).getNumericCellValue());
	}
	
	public void testBuildingDontExist()
	{
		XSSFWorkbook wb = SpreadsheetExporter.createLockerList(UUID.randomUUID());
		assertNull(wb);
	}
}
