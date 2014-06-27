package lms.businesslogic;

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

}
