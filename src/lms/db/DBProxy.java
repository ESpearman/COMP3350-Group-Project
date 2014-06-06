package lms.db;

import java.util.ArrayList;
import java.util.UUID;

import lms.business.Locker;
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
	
	public static Locker getLockerById(UUID id)
	{
		return StubDB.getLockerById(id);
	}
	
	public static void saveTerm(Term term)
	{
		StubDB.saveTerm(term);
	}
	
	public static void saveStudent(Student student)
	{
		StubDB.saveStudent(student);
	}
	
	public static void saveLocker(Locker locker)
	{
		StubDB.saveLocker(locker);
	}
	
	public static ArrayList<Term> getAllTerms()
	{
		return StubDB.getAllTerms();
	}
	
	public static ArrayList<Student> getStudentsListByTerm(UUID term)
	{
		return StubDB.getStudentsListByTerm(term);
	}
	
	public static ArrayList<Locker> getLocerksListByTerm(UUID term)
	{
		return StubDB.getLockersListByterm(term);
	}
	
	public static Student getStudentByNumber(int number)
	{
		return StubDB.getStudentByNumber(number);
	}
	
	public static Locker getLockerByNumber(int number)
	{
		return StubDB.getLockerByNumber(number);
	}
}
