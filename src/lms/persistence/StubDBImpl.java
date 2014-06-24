package lms.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.UUID;

import lms.business.Building;
import lms.business.BusinessObject;
import lms.business.Locker;
import lms.business.Rental;
import lms.business.Student;
import lms.business.Term;
import lms.business.TermBased;

public class StubDBImpl implements IDB
{
	
	private ArrayList<BusinessObject> terms;
	private ArrayList<BusinessObject> students;
	private ArrayList<BusinessObject> lockers;
	private ArrayList<BusinessObject> buildings;
	private ArrayList<BusinessObject> rentals;
	
	public StubDBImpl()
	{
		terms = new ArrayList<BusinessObject>();
		students = new ArrayList<BusinessObject>();
		lockers = new ArrayList<BusinessObject>();
		buildings = new ArrayList<BusinessObject>();
		rentals = new ArrayList<BusinessObject>();
	}
	
	public void resetDB()
	{
		terms.clear();
		students.clear();
		lockers.clear();
		buildings.clear();
		rentals.clear();
	}
	
	@Override
	public Term getTermById(UUID id, Connection ... conn)
	{
		BusinessObject obj = getById(terms, id);
		if(obj != null && obj instanceof Term)
		{
			return (Term)obj.clone();
		}
		
		return null;
	}
	
	@Override
	public Student getStudentById(UUID id, Connection ... conn)
	{
		BusinessObject obj = getById(students, id);
		if(obj != null && obj instanceof Student)
		{
			return (Student)obj.clone();
		}
		
		return null;
	}
	
	@Override
	public Locker getLockerById(UUID id, Connection ... conn)
	{
		BusinessObject obj = getById(lockers, id);
		if(obj != null && obj instanceof Locker)
		{
			return (Locker)obj.clone();
		}
		
		return null;
	}
	
	@Override
	public Building getBuildingById(UUID id, Connection ... conn)
	{
		BusinessObject obj = getById(buildings, id);
		if(obj != null && obj instanceof Building)
		{
			return (Building)obj.clone();
		}
		
		return null;
	}
	
	@Override
	public Rental getRentalById(UUID id, Connection ... conn)
	{
		BusinessObject obj = getById(rentals, id);
		if(obj != null && obj instanceof Rental)
		{
			return (Rental)obj.clone();
		}
		
		return null;
	}
	
	@Override
	public void saveTerm(Term term, Connection ... conn)
	{
		save(terms, term);
	}
	
	@Override
	public void saveStudent(Student student, Connection ... conn)
	{
		save(students, student);
	}
	
	@Override
	public void saveLocker(Locker locker, Connection ... conn)
	{
		save(lockers, locker);
	}
	
	@Override
	public void saveBuilding(Building building, Connection ... conn)
	{
		save(buildings, building);
	}
	
	@Override
	public void saveRental(Rental rental, Connection ... conn)
	{
		save(rentals, rental);
	}
	
	@Override
	public ArrayList<Student> getStudentsListByTerm(UUID term, Connection ... conn)
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
	
	@Override
	public ArrayList<Locker> getLockersListByTerm(UUID term, Connection ... conn)
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
	
	@Override
	public ArrayList<Rental> getRentalsListByTerm(UUID term, Connection ... conn)
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
	
	@Override
	public Student getStudentByNumberAndTerm(int number, UUID term, Connection ... conn)
	{
		//TODO add term filter
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

	@Override
	public Locker getLockerByNumberBuildingAndTerm(int number, UUID building, UUID term, Connection ... conn)
	{
		//TODO add building and term filters
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

	@Override
	public ArrayList<Locker> getFreeLockersForBuildingAndTerm(UUID building, UUID term, Connection ... conn)
	{
		//TODO implement
		return null;
	}
	
	@Override
	public Rental getRentalByLocker(UUID locker, Connection ... conn)
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
	
	@Override
	public Rental getRentalByStudent(UUID student, Connection ... conn)
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
	
	@Override
	public ArrayList<Term> getAllTerms(Connection ... conn)
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
	
	@Override
	public ArrayList<Building> getAllBuildings(Connection ... conn)
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
	
	private BusinessObject getById(ArrayList<BusinessObject> from, UUID id)
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
	
	private ArrayList<TermBased> getListByTerm(ArrayList<BusinessObject> from, UUID term)
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
	
	private void save(ArrayList<BusinessObject> to, BusinessObject unsaved)
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
