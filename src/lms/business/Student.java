package lms.business;

import java.util.ArrayList;
import java.util.UUID;

import lms.db.DBProxy;
import lombok.Getter;
import lombok.Setter;

public class Student implements BusinessObject
{
	@Getter
	private UUID id;
	
	@Getter
	@Setter
	private String firstName;
	
	@Getter
	@Setter
	private String lastName;
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	private int studentNumber;
	
	@Getter
	@Setter
	private boolean scienceStudent;
	
	@Getter
	private UUID term;
	
	public Student(UUID id, String firstName, String lastName, String email, int studentNumber,
			boolean scienceStudent, UUID term)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.studentNumber = studentNumber;
		this.scienceStudent = scienceStudent;
		this.term = term;
	}
	
	public Student(String firstName, String lastName, String email, int studentNumber,
			boolean scienceStudent, UUID term)
	{
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.studentNumber = studentNumber;
		this.scienceStudent = scienceStudent;
		this.term = term;
	}
	
	@Override
	public BusinessObject clone()
	{
		return (BusinessObject)(new Student(id, firstName, lastName, email, studentNumber, scienceStudent, term));
	}

	@Override
	public void save()
	{
		DBProxy.saveStudent(this);
	}
	
	public static Student getById(UUID id)
	{
		return DBProxy.getStudentById(id);
	}
	
	public static Student getByStudentNumber(int number)
	{
		return null;
	}
	
	public static ArrayList<Student> getAll()
	{
		return null;
	}
	
	public static ArrayList<Student> getListByTerm(UUID term)
	{
		return null;
	}
	
}
