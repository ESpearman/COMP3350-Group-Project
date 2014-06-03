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
		return (Term)getById(terms, id);
	}
	
	public static Student getStudentById(UUID id)
	{
		return (Student)getById(students, id);
	}
	
	public static Term saveTerm(Term term)
	{
		return (Term)save(terms, term);
	}
	
	public static Student saveStudent(Student student)
	{
		return (Student)save(students, student);
	}
	
	private static BusinessObject getById(ArrayList<BusinessObject> from, UUID id)
	{
		for(BusinessObject obj : from)
		{
			if(obj.getId().equals(id))
			{
				return obj;
			}
		}
		
		return null;
	}
	
	private static BusinessObject save(ArrayList<BusinessObject> to, BusinessObject unsaved)
	{
		for(int i = 0; i < to.size(); i++)
		{
			if(to.get(i).getId().equals(unsaved.getId()))
			{
				to.set(i, unsaved);
			}
		}
		
		to.add(unsaved);
		
		return unsaved;
	}
}
