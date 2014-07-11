package lms.businesslogic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import lms.domainobjects.Building;
import lms.domainobjects.Locker;
import lms.domainobjects.Rental;
import lms.domainobjects.Student;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class SpreadsheetExporter 
{
	public static void writeRentalList(String filepath)
	{
		XSSFWorkbook workbook = createRentalList();
		saveSpreadsheet(workbook, filepath);
	}
	
	public static void saveStudentList(String filepath)
	{
		Workbook workbook = createStudentList();
		saveSpreadsheet(workbook, filepath);
	}
	
	public static void writeLockerList(UUID buildingID, String filepath)
	{
		XSSFWorkbook workbook = createLockerList(buildingID);
		saveSpreadsheet(workbook, filepath);
	}
	
	public static XSSFWorkbook createRentalList()
	{
		int studentNumCol = 0;
		int firstNameCol = 1;
		int lastNameCol = 2;
		int buildingCol = 3;
		int lockerNumCol = 4;
		
		int[] colOrder = {studentNumCol, firstNameCol, lastNameCol, buildingCol, lockerNumCol};
		String[] colTitles = {"StudentNumber", "First Name", "Last Name", "Building", "Locker Number"};
		String sheetTitle = CurrentTermInfo.currentTerm.getName() + " Rentals";
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		
		initSheet(sheet, sheetTitle, colOrder, colTitles);
		
		ArrayList<Rental> rentals = Rental.getListByTerm(CurrentTermInfo.currentTerm.getId());
		int currentRowIndex = 2;
		
		for(Rental rental : rentals)
		{
			Row currentRow = sheet.createRow(currentRowIndex++);
			Student currentStudent = Student.getById(rental.getStudent());
			Locker currentLocker = Locker.getById(rental.getLocker());
			Building currentBuilding = Building.getById(currentLocker.getBuilding());
			
			currentRow.createCell(studentNumCol).setCellValue(currentStudent.getStudentNumber());
			currentRow.createCell(firstNameCol).setCellValue(currentStudent.getFirstName());
			currentRow.createCell(lastNameCol).setCellValue(currentStudent.getLastName());
			currentRow.createCell(buildingCol).setCellValue(currentBuilding.getName());
			currentRow.createCell(lockerNumCol).setCellValue(currentLocker.getNumber());
		}
		
		autoSizeSheet(sheet);
		return workbook;
	}

	public static XSSFWorkbook createLockerList(UUID buildingID)
	{
		if(Building.getById(buildingID) == null)
		{
			return null;
		}
		
		int numberCol = 0;
		int sizeCol = 1;
		int assignedCol = 2;
		
		String buildingName = Building.getById(buildingID).getName();
		
		int[] colOrder = {numberCol, sizeCol, assignedCol};
		String[] colTitles = {"Locker Number", "Locker Size", "Status"};
		String sheetTitle = buildingName + " Lockers";
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		
		initSheet(sheet, sheetTitle, colOrder, colTitles);
		
		ArrayList<Locker> lockerList = Locker.getListByTerm(CurrentTermInfo.currentTerm.getId());
		int currentRowIndex = 2;
		
		for(Locker locker : lockerList)
		{
			Row currentRow = sheet.createRow(currentRowIndex++);
			
			currentRow.createCell(numberCol).setCellValue(locker.getNumber());
			currentRow.createCell(sizeCol).setCellValue(locker.getSizeString());
			
			if(locker.isRented())
			{
				currentRow.createCell(assignedCol).setCellValue("Rented");
			}
			else
			{
			currentRow.createCell(assignedCol).setCellValue("Free");
			}
		}
		
		autoSizeSheet(sheet);
		return workbook;
	}
	
	public static XSSFWorkbook createStudentList()
	{
		int studentNumberCol = 0;
		int firstNameCol = 1;
		int lastNameCol = 2;
		int emailCol = 3;
		int scienceCol = 4;
		
		int[] colOrder = {studentNumberCol, firstNameCol, lastNameCol, emailCol, scienceCol};
		String[] colTitles = {"Student Number", "First Name", "Last Name", "Email", "Faculty"};
		String sheetTitle = "Current Students";
				
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		
		initSheet(sheet, sheetTitle, colOrder, colTitles);
		
		ArrayList<Student> students = Student.getListByTerm(CurrentTermInfo.currentTerm.getId());
		int currentRowIndex = 2;
		
		for(Student student : students)
		{
			Row currentRow = sheet.createRow(currentRowIndex++);
			
			currentRow.createCell(studentNumberCol).setCellValue(student.getStudentNumber());
			currentRow.createCell(firstNameCol).setCellValue(student.getFirstName());
			currentRow.createCell(lastNameCol).setCellValue(student.getLastName());
			currentRow.createCell(emailCol).setCellValue(student.getEmail());
			
			if(student.isScienceStudent())
			{
				currentRow.createCell(scienceCol).setCellValue("Science");
			}
			else
			{
				currentRow.createCell(scienceCol).setCellValue("Other");
			}
			
		}
		
		autoSizeSheet(sheet);
		return workbook;
	}
	
	private static void autoSizeSheet(XSSFSheet sheet)
	{
		for(int i = 0; i < sheet.getRow(1).getLastCellNum(); i++)
		{
			sheet.autoSizeColumn(i);
		}
	}
	
	public static void initSheet(XSSFSheet sheet, String sheetTitle, int[] colOrder, String[] colTitles) 
	{
		if(sheet != null)
		{
			Row currentRow = sheet.createRow(0);
			currentRow.createCell(0).setCellValue(sheetTitle);
			currentRow = sheet.createRow(1);
			
			for(int i = 0; i < colTitles.length; i++)
			{
				currentRow.createCell(colOrder[i]).setCellValue(colTitles[i]);
			}
		}
	}

	public static void saveSpreadsheet(Workbook spreadsheet, String filename)
	{
		try
        {
            FileOutputStream file = new FileOutputStream(new File(filename));
            spreadsheet.write(file);
            file.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
	}
}