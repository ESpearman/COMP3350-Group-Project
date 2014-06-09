package lms.business;

import java.util.UUID;

public class RegisterStudent
{
	public static Student upsertStudent(Student searchedStudent, String firstName, String lastName, String email, int studentNumber,
			 boolean scienceStudent, UUID term)
	{
		if(searchedStudent == null)
		{
			return insertStudent(firstName, lastName, email, studentNumber, scienceStudent, term);
		}
		else
		{
			return updateStudent(searchedStudent, firstName, lastName, email, scienceStudent);
		}
	}
	
	private static Student insertStudent(String firstName, String lastName, String email, int studentNumber,
									 boolean scienceStudent, UUID term)
	{
		
		Student theStudent = new Student(firstName, lastName, email, studentNumber, scienceStudent, term);
		theStudent.save();
		return theStudent;
	}
	
	public static Student updateStudent(Student searchedStudent, String firstName, String lastName, String email,
			 boolean scienceStudent)
	{
		searchedStudent.setFirstName(firstName);
		searchedStudent.setLastName(lastName);
		searchedStudent.setEmail(email);
		searchedStudent.setScienceStudent(scienceStudent);
		searchedStudent.save();
		return searchedStudent;
	}
}
