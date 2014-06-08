package lms.business.logic;

import lms.business.Student;

public class SearchStudent
{
	public static Student getByStudentNumber(int studentID)
	{
		return Student.getByStudentNumber(studentID);
	}
}
