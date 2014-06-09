package lms.business.logic;

import java.util.UUID;

import lms.business.Student;

public class RegisterStudent
{
	public static void upsertStudent(Student searchedStudent, String firstName, String lastName, String email, int studentNumber,
			 boolean scienceStudent, UUID term)
	{
		if(searchedStudent == null)
		{
			insertStudent(firstName, lastName, email, studentNumber, scienceStudent, term);
		}
		else
		{
			updateStudent(searchedStudent, firstName, lastName, email, scienceStudent);
		}
	}
	
	private static void insertStudent(String firstName, String lastName, String email, int studentNumber,
									 boolean scienceStudent, UUID term)
	{
		
		Student theStudent = new Student(firstName, lastName, email, studentNumber, scienceStudent, term);
		theStudent.save();

	}
	
	public static void updateStudent(Student searchedStudent, String firstName, String lastName, String email,
			 boolean scienceStudent)
	{
		searchedStudent.setFirstName(firstName);
		searchedStudent.setLastName(lastName);
		searchedStudent.setEmail(email);
		searchedStudent.setScienceStudent(scienceStudent);
		searchedStudent.save();
	}
}
