package lms.stubdb;

import java.util.ArrayList;
import java.util.UUID;

import lms.business.Building;
import lms.business.BusinessObject;
import lms.business.Locker;
import lms.business.Rental;
import lms.business.Student;
import lms.business.Term;
import lms.business.TermBased;

public class StubDB
{
	private static ArrayList<BusinessObject> terms;
	private static ArrayList<BusinessObject> students;
	private static ArrayList<BusinessObject> lockers;
	private static ArrayList<BusinessObject> buildings;
	private static ArrayList<BusinessObject> rentals;
	
	public static void resetDB()
	{
		terms.clear();
		students.clear();
		lockers.clear();
		buildings.clear();
		rentals.clear();
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
	
	public static Locker getLockerById(UUID id)
	{
		BusinessObject obj = getById(lockers, id);
		if(obj != null && obj instanceof Locker)
		{
			return (Locker)obj.clone();
		}
		
		return null;
	}
	
	public static Building getBuildingById(UUID id)
	{
		BusinessObject obj = getById(buildings, id);
		if(obj != null && obj instanceof Building)
		{
			return (Building)obj.clone();
		}
		
		return null;
	}
	
	public static Rental getRentalById(UUID id)
	{
		BusinessObject obj = getById(rentals, id);
		if(obj != null && obj instanceof Rental)
		{
			return (Rental)obj.clone();
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
	
	public static void saveLocker(Locker locker)
	{
		save(lockers, locker);
	}
	
	public static void saveBuilding(Building building)
	{
		save(buildings, building);
	}
	
	public static void saveRental(Rental rental)
	{
		save(rentals, rental);
	}
	
	public static ArrayList<Student> getStudentsListByTerm(UUID term)
	{
		ArrayList<TermBased> genericList = getListByTerm(students, term);
		ArrayList<Student> cast = new ArrayList<Student>();
		for(TermBased obj: genericList)
		{
			if(obj instanceof Student)
			{
				cast.add((Student)obj);
			}
		}
		
		return cast;
	}
	
	public static ArrayList<Locker> getLockersListByTerm(UUID term)
	{
		ArrayList<TermBased> genericList = getListByTerm(lockers, term);
		ArrayList<Locker> cast = new ArrayList<Locker>();
		for(TermBased obj: genericList)
		{
			if(obj instanceof Locker)
			{
				cast.add((Locker)obj);
			}
		}
		
		return cast;
	}
	
	public static ArrayList<Rental> getRentalsListByTerm(UUID term)
	{
		ArrayList<TermBased> genericList = getListByTerm(rentals, term);
		ArrayList<Rental> cast = new ArrayList<Rental>();
		for(TermBased obj: genericList)
		{
			if(obj instanceof Rental)
			{
				cast.add((Rental)obj);
			}
		}
		
		return cast;
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
	
	public static Locker getLockerByNumber(int number)
	{
		for(BusinessObject obj: lockers)
		{
			if(obj instanceof Locker)
			{
				Locker locker = (Locker)obj;
				if(locker.getNumber() == number)
				{
					return (Locker)locker.clone();
				}
			}
		}
		
		return null;
	}
	
	public static Rental getRentalByLocker(UUID locker)
	{
		for(BusinessObject obj: rentals)
		{
			if(obj instanceof Rental)
			{
				Rental rental = (Rental)obj;
				if(rental.getLocker().equals(locker))
				{
					return (Rental)rental.clone();
				}
			}
		}
		
		return null;
	}
	
	public static Rental getRentalByStudent(UUID student)
	{
		for(BusinessObject obj: rentals)
		{
			if(obj instanceof Rental)
			{
				Rental rental = (Rental)obj;
				if(rental.getStudent().equals(student))
				{
					return (Rental)rental.clone();
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
	
	public static ArrayList<Building> getAllBuildings()
	{
		ArrayList<Building> to = new ArrayList<Building>();
		
		for(BusinessObject obj: buildings)
		{
			BusinessObject theClone = obj.clone();
			if(theClone instanceof Building)
			{
				to.add((Building)theClone);
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
	
	private static ArrayList<TermBased> getListByTerm(ArrayList<BusinessObject> from, UUID term)
	{
		ArrayList<TermBased> result = new ArrayList<TermBased>();
		for(BusinessObject obj : from)
		{
			if(obj instanceof TermBased)
			{
				TermBased termBased = (TermBased)obj;
				if(termBased.getTerm().equals(term))
				{
					result.add((TermBased)termBased.clone());
				}
			}
			
		}
		
		return result;
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
