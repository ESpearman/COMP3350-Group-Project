package lms.businesslogic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import lms.domainobjects.Building;
import lms.domainobjects.Locker;
import lms.domainobjects.LockerSize;
import lms.domainobjects.Student;
import lombok.Getter;
import lombok.Setter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class SpreadsheetImporter 
{
	@Getter
	@Setter
	private static String status;
	private static int maxChars = 100;
	
	public static void importStudents(String path)
	{
		try 
		{
			Sheet studentSheet = getSheet(path);
			status = "Student import completed succesfully.";

			for (Row row : studentSheet) 
			{
				addScienceStudent(row);
			}

		}
		
		catch(IOException ioe) 
		{
			ioe.printStackTrace();
		}
		
		catch(InvalidFormatException ife) 
		{
			ife.printStackTrace();
		}
	}
	
	public static void importLockers(String path)
	{
		try 
		{
			Workbook lockerBook = getWorkbook(path);
			status = "Locker import completed successfully";

			for (int i = 0; i < lockerBook.getNumberOfSheets(); i++) 
			{
				Sheet sheet = lockerBook.getSheetAt(i);
				
				for(Row row : sheet)
				{
					addLocker(row);
				}
			}
		}
		
		catch(IOException ioe) 
		{
			ioe.printStackTrace();
		}
		
		catch(InvalidFormatException ife) 
		{
			ife.printStackTrace();
		}
	}

	private static Sheet getSheet(String path) throws 
		IOException,
		InvalidFormatException 
	{
		Workbook workbook = WorkbookFactory.create(new File(path));
		Sheet sheet = workbook.getSheetAt(0);
		return sheet;
	}
	
	private static Workbook getWorkbook(String path) throws 
		IOException,
		InvalidFormatException 
	{
		Workbook workbook = WorkbookFactory.create(new File(path));
		return workbook;
	}

	private static void addScienceStudent(Row row)
	{
		Student currStudent;
		String firstName;
		String lastName;
		String email;
		int studentNumber;
		boolean isScienceStudent = true;
		
		if(isValidStudent(row))
		{
			studentNumber = (int)row.getCell(0).getNumericCellValue();
			
			if(row.getCell(1) == null)
			{
				firstName = "";
			}
			
			else
			{
				firstName = row.getCell(1).getStringCellValue();
			}
			
			if(row.getCell(2) == null)
			{
				lastName = "";
			}
			
			else
			{
				lastName = row.getCell(2).getStringCellValue();
			}
			
			if(row.getCell(3) == null)
			{
				email = "";
			}
			
			else
			{
				email = row.getCell(3).getStringCellValue();
			}
			
			currStudent = new Student(firstName, lastName, email, studentNumber, isScienceStudent, CurrentTermInfo.currentTerm.getId());
			currStudent.save();
		}
	}

	private static void addLocker(Row row)
	{
		Locker currLocker;
		Building building = null;
		int lockerNumber;
		LockerSize size;

		if(isValidLocker(row))
		{
			String buildingName = row.getSheet().getSheetName();
			building = determineBuilding(buildingName);

			lockerNumber = (int)row.getCell(0).getNumericCellValue();
			size = determineLockerSize(row.getCell(1).getStringCellValue());
			
			currLocker = new Locker(CurrentTermInfo.currentTerm.getId(), lockerNumber, building.getId(), size);
			currLocker.save();
		}
	}

	private static Building determineBuilding(String buildingName)
	{
		ArrayList<Building> existingBuildings = Building.getAll();
		Building result = null;
		
		for(Building existingBuilding : existingBuildings)
		{
			if(buildingName.equals(existingBuilding.getName()))
			{
				result = existingBuilding;
			}
		}
		
		if(result == null)
		{
			result = new Building(buildingName);
			result.save();
		}
		
		return result;
	}
	
	private static LockerSize determineLockerSize(String stringCellValue)
	{
		if(stringCellValue.equals("FULL"))
		{
			return LockerSize.FULL;
		}
		else
		{
			return LockerSize.HALF;
		}
	}
	
	private static boolean isValidStudent(Row row)
	{
		boolean result = true;

		if(isEmptyRow(row))
		{
			result = false;
		}
		
		else if (row.getCell(0).getCellType() != Cell.CELL_TYPE_NUMERIC)
		{
			result = false;
		}
		
		if(result == true)
		{
			for(Cell cell : row)
			{
				if(cell.getCellType() == Cell.CELL_TYPE_STRING 
						&& cell.getStringCellValue().length() > 50)
				{
					result = false;
					
					status = "Warning: Some students may have not been imported correctly!\n"
							+ "Please ensure spreadsheet has proper format: "
							+ "first name, last name, email, student number. \nMax chars per field: " + maxChars;
				}
			}
		}
		
		return result;
	}
	
	private static boolean isValidLocker(Row row)
	{
		boolean result = true;
		int numLockerFields = 2;
		int[] lockerFieldTypes = {Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_STRING};
		
		if(isEmptyRow(row))
		{
			result = false;
		}
		
		else
		{
			for(int i = 0; i < numLockerFields; i++)
			{
				if(row.getCell(i) == null)
				{
					result = false;
				}
				
				else if (row.getCell(i).getCellType() != lockerFieldTypes[i])
				{
					result = false;
				}
			}
		}
		
		if(result == true)
		{
			for(Cell cell : row)
			{
				if(cell.getCellType() == Cell.CELL_TYPE_STRING 
						&& cell.getStringCellValue().length() > 50)
				{
					result = false;
					status = "Warning: Some lockers may have not been imported correctly!"
							+ "Please ensure spreadsheet has proper format: "
							+ "building name, locker number, locker size. Max chars per field:" + maxChars;
				}
			}
		}
		
		return result;
	}
	
	private static boolean isEmptyRow(Row row)
	{
		for(Cell cell : row)
		{
			if(cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
			{
				return false;
			}
		}
		
		return true;
	}
}
