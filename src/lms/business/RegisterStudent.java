package lms.business;

import java.util.ArrayList;
import java.util.UUID;

import lms.db.DBProxy;
import lombok.Getter;

public class RegisterStudent // I am not sure what to implement here, implements BusinessObject
{
	@Getter
	private static UUID id; 
	
	public RegisterStudent(UUID id)
	{
		RegisterStudent.id = id;
	}
	public static void createStudent(UUID id, String firstName, String lastName, String email, int studentNumber,
									 boolean scienceStudent, UUID term)
	{
		Student newStudent = null;
		@SuppressWarnings("static-access")
		//I think i need search here!!!
		Student otherStudent = otherStudent.getByStudentNumber(studentNumber); 
		if(otherStudent != null)
		{
			newStudent  = new Student(firstName, lastName, email, studentNumber, scienceStudent, term);
		}
		else
		{
			newStudent = new Student(id, firstName, lastName, email, studentNumber, scienceStudent, term); 
		}
		newStudent.save();

	}
	
	/*
	@Override
	public BusinessObject clone()
	{
		return (BusinessObject)(new Student(id, firstName, lastName, email, studentNumber, scienceStudent, term));
	}
	 */

	

}
