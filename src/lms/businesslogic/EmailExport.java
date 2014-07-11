package lms.businesslogic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import lms.domainobjects.Rental;
import lms.domainobjects.Student;

public class EmailExport
{	
	public static String exportStudentEmails()
	{
		ArrayList<Rental> rentals = Rental.getListByTerm(CurrentTermInfo.currentTerm.getId());
		StringBuilder result = new StringBuilder();
		char delimiter = ';';
		
		if(rentals.size() == 0)
		{
			result.append("No students currently registered");
		}
		
		else
		{
			for(Rental rental : rentals)
			{
				Student currStudent = Student.getById(rental.getStudent());
				
				result.append(currStudent.getEmail());
				result.append(delimiter);
			}
		}
		
		return result.toString();
	}
	
	public static void writeToFile(String emails, String filepath) throws IOException
	{
		File textFile = new File(filepath);
		BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
		writer.write(emails);
		writer.close();
	}
}
