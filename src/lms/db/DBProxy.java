package lms.db;

import java.util.ArrayList;
import java.util.UUID;

import lms.business.Student;
import lms.business.Term;
import lms.stubdb.StubDB;

public class DBProxy
{
	
	public static Term getTermById(UUID id)
	{
		return StubDB.getTermById(id);
	}
	
	public static Student getStudentById(UUID id)
	{
		return StubDB.getStudentById(id);
	}
	
	public static void saveTerm(Term term)
	{
		StubDB.saveTerm(term);
	}
	
	public static void saveStudent(Student student)
	{
		StubDB.saveStudent(student);
	}
	
	public static ArrayList<Term> getAllTerms()
	{
		return StubDB.getAllTerms();
	}
	
	public static ArrayList<Student> getAllStudents()
	{
		return StubDB.getAllStudents();
	}
	
	public static Student getStudentByNumber(int number)
	{
		return StubDB.getStudentByNumber(number);
	}
}
