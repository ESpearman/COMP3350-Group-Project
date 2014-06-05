package lms.stubdb;

import java.util.ArrayList;
import java.util.UUID;

import lms.business.BusinessObject;
import lms.business.Student;
import lms.business.Term;

public class StubDB
{
	private static ArrayList<BusinessObject> terms;
	private static ArrayList<BusinessObject> students;
	
	public static void resetDB()
	{
		terms.clear();
		students.clear();
	}
	
	public static Term getTermById(UUID id)
	{
		BusinessObject obj = getById(terms, id);
		if(obj != null && obj instanceof Term)
		{
			return (Term)obj.clone();
		}
		
		return null;
	}
	
	public static Student getStudentById(UUID id)
	{
		BusinessObject obj = getById(students, id);
		if(obj != null && obj instanceof Student)
		{
			return (Student)obj.clone();
		}
		
		return null;
	}
	
	public static void saveTerm(Term term)
	{
		save(terms, term);
	}
	
	public static void saveStudent(Student student)
	{
		save(students, student);
	}
	
	public static Student getStudentByNumber(int number)
	{
		for(BusinessObject obj: students)
		{
			if(obj instanceof Student)
			{
				Student student = (Student)obj;
				if(student.getStudentNumber() == number)
				{
					return (Student)student.clone();
				}
			}
		}
		
		return null;
	}
	
	public static ArrayList<Term> getAllTerms()
	{
		ArrayList<Term> to = new ArrayList<Term>();
		
		for(BusinessObject obj: terms)
		{
			BusinessObject theClone = obj.clone();
			if(theClone instanceof Term)
			{
				to.add((Term)theClone);
			}
		}
		
		return to;
	}
	
	public static ArrayList<Student> getAllStudents()
	{
		ArrayList<Student> to = new ArrayList<Student>();
		
		for(BusinessObject obj: students)
		{
			BusinessObject theClone = obj.clone();
			if(theClone instanceof Student)
			{
				to.add((Student)theClone);
			}
		}
		
		return to;
	}
	
	private static BusinessObject getById(ArrayList<BusinessObject> from, UUID id)
	{
		for(BusinessObject obj : from)
		{
			if(obj.getId().equals(id))
			{
				return obj.clone();
			}
		}
		
		return null;
	}
	
	private static void save(ArrayList<BusinessObject> to, BusinessObject unsaved)
	{
		for(int i = 0; i < to.size(); i++)
		{
			if(to.get(i).getId().equals(unsaved.getId()))
			{
				to.set(i, unsaved.clone());
			}
		}
		
		to.add(unsaved.clone());
	}
}
