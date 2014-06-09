package lms.business;

import java.util.UUID;

public class RegisterStudent
{
	public static void upsertStudent(Student searchedStudent, String firstName, String lastName, String email, int studentNumber,
			 boolean scienceStudent, UUID term)
	{
<<<<<<< HEAD
		Student newStudent = null;
		@SuppressWarnings("static-access")
		//I think i need search here!!!
		Student otherStudent = Student.getByStudentNumber(studentNumber); 
		if(otherStudent != null)
=======
		if(searchedStudent == null)
>>>>>>> 321c7fffb52c45cc50b5430e052abf80bf271cd6
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
