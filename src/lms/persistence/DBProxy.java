package lms.persistence;

import java.util.ArrayList;
import java.util.UUID;

import lms.business.Building;
import lms.business.Locker;
import lms.business.Rental;
import lms.business.Student;
import lms.business.Term;

import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DBProxy implements DBInjectable
{
	//This class is only meant to be used as an interface to the database for business objects
	//Do not call these methods - DB queries should be made using the methods in the business objects
	
	public static DBProxy instance;
	
	@Setter
	private IDB currentDB;
	
	public void resetDB()
	{
		currentDB.resetDB();
	}
	
	public Term getTermById(UUID id)
	{
		return currentDB.getTermById(id);
	}
	
	public Student getStudentById(UUID id)
	{
		return currentDB.getStudentById(id);
	}
	
	public Locker getLockerById(UUID id)
	{
		return currentDB.getLockerById(id);
	}
	
	public Building getBuildingById(UUID id)
	{
		return currentDB.getBuildingById(id);
	}
	
	public Rental getRentalById(UUID id)
	{
		return currentDB.getRentalById(id);
	}
	
	public void saveTerm(Term term)
	{
		currentDB.saveTerm(term);
	}
	
	public void saveStudent(Student student)
	{
		currentDB.saveStudent(student);
	}
	
	public void saveLocker(Locker locker)
	{
		currentDB.saveLocker(locker);
	}
	
	public void saveBuilding(Building building)
	{
		currentDB.saveBuilding(building);
	}
	
	public void saveRental(Rental rental)
	{
		currentDB.saveRental(rental);
	}
	
	public ArrayList<Term> getAllTerms()
	{
		return currentDB.getAllTerms();
	}
	
	public ArrayList<Building> getAllBuildings()
	{
		return currentDB.getAllBuildings();
	}
	
	public ArrayList<Student> getStudentsListByTerm(UUID term)
	{
		return currentDB.getStudentsListByTerm(term);
	}
	
	public ArrayList<Locker> getLockersListByTerm(UUID term)
	{
		return currentDB.getLockersListByTerm(term);
	}
	
	public ArrayList<Rental> getRentalsListByTerm(UUID term)
	{
		return currentDB.getRentalsListByTerm(term);
	}
	
	public Student getStudentByNumberAndTerm(int number, UUID term)
	{
		return currentDB.getStudentByNumberAndTerm(number, term);
	}
	
	public Locker getLockerByNumberBuildingAndTerm(int number, UUID building, UUID term)
	{
		return currentDB.getLockerByNumberBuildingAndTerm(number, building, term);
	}
	
	public Rental getRentalByLocker(UUID locker)
	{
		return currentDB.getRentalByLocker(locker);
	}
	
	public Rental getRentalByStudent(UUID student)
	{
		return currentDB.getRentalByStudent(student);
	}
	
	public ArrayList<Locker> getFreeLockersForBuildingAndTerm(UUID building, UUID term)
	{
		return currentDB.getFreeLockersForBuildingAndTerm(building, term);
	}
}
