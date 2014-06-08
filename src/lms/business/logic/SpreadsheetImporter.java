package lms.business.logic;

import java.util.UUID;
import java.io.File;
import java.io.IOException;

import lms.business.Student;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class SpreadsheetImporter 
{
	public static void importStudents(String relativePath) 
	{
		try 
		{
			String absolutePath = new File("").getAbsolutePath();
			String fullPath = absolutePath + relativePath;
			
			Workbook workbook = WorkbookFactory.create(new File(fullPath));
			Sheet studentSheet = workbook.getSheetAt(0);

			Student currStudent;
			UUID term = UUID.randomUUID();
			boolean isScienceStudent = true;

			for (Row row : studentSheet) 
			{
				String firstName;
				String lastName;
				String email;
				int studentNumber;

				firstName = row.getCell(0).getStringCellValue();
				lastName = row.getCell(1).getStringCellValue();
				email = row.getCell(2).getStringCellValue();
				studentNumber = (int)row.getCell(3).getNumericCellValue();

				currStudent = new Student(firstName, lastName, email, studentNumber, isScienceStudent, term);
				currStudent.save();
			}

		}
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		}
		catch (InvalidFormatException ife) 
		{
			ife.printStackTrace();
		}

	}
}
