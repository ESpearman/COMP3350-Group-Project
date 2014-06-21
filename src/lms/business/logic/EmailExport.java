package lms.business.logic;

import java.util.ArrayList;
import lms.business.Student;

public class EmailExport
{	
	public static String exportStudentEmails()
	{
		ArrayList<Student> students = Student.getListByTerm(CurrentTermInfo.currentTerm.getId());
		StringBuilder result = new StringBuilder();
		char delimiter = ';';
		
		if(students.size() == 0)
		{
			result.append("No students currently registered");
		}
		else
		{
			for(Student currStudent : students)
			{
				result.append(currStudent.getEmail());
				result.append(delimiter);
			}
		}
		
		return result.toString();
	}

}
