package lms.business.logic;

import lms.business.Student;

public class SearchStudent
{
	public static Student getByStudentNumber(String studentID)
	{
		int length  = studentID.length();
		int studentNumber;
		
		if(length >= 7 && length <= 10)
		{
			try //try to convert string to int
			{
				studentNumber = Integer.parseInt(studentID);
				return Student.getByStudentNumber(studentNumber, CurrentTermInfo.currentTerm.getId());
			}
			catch(NumberFormatException e) //characters were input
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
}
