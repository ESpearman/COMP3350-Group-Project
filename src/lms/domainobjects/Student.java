package lms.domainobjects;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import lms.persistence.DBProxy;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

public class Student implements TermBased
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
		this(UUID.randomUUID(), firstName, lastName, email, studentNumber, scienceStudent, term);
	}
	
	@Override
	public BusinessObject clone()
	{
		return (BusinessObject)(new Student(id, firstName, lastName, email, studentNumber, scienceStudent, term));
	}

	@Override
	public void save()
	{
		DBProxy.instance.saveStudent(this);
	}
	
	public static Student getById(UUID id)
	{
		return DBProxy.instance.getStudentById(id);
	}
	
	public static Student getByStudentNumber(int number, UUID term)
	{
		return DBProxy.instance.getStudentByNumberAndTerm(number, term);
	}
	
	public static ArrayList<Student> getListByTerm(UUID term)
	{
		return DBProxy.instance.getStudentsListByTerm(term);
	}
	
	public static Student parse(ResultSet result)
	{
		try
		{
			val id = result.getString("id");
			val firstName = result.getString("first_name");
			val lastName = result.getString("last_name");
			val email = result.getString("email");
			val studentNumber = result.getInt("student_number");
			val scienceStudent = result.getBoolean("science_student");
			val term = result.getString("term");
			
			val idUUID = UUID.fromString(id);
			val termUUID = UUID.fromString(term);
			
			return new Student(idUUID, firstName, lastName, email, studentNumber, scienceStudent, termUUID);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
		
	}

}
