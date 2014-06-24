package lms.db;

import java.util.ArrayList;
import java.util.UUID;

import lms.business.Building;
import lms.business.Locker;
import lms.business.Rental;
import lms.business.Student;
import lms.business.Term;
import lms.persistence.CurrentDB;

public class DBProxy
{
	//This class is only meant to be used as an interface to the database for business objects
	//Do not call these methods - DB queries should be made using the methods in the business objects
	
	public static Term getTermById(UUID id)
	{
		return CurrentDB.currentDB.getTermById(id);
	}
	
	public static Student getStudentById(UUID id)
	{
		return CurrentDB.currentDB.getStudentById(id);
	}
	
	public static Locker getLockerById(UUID id)
	{
		return CurrentDB.currentDB.getLockerById(id);
	}
	
	public static Building getBuildingById(UUID id)
	{
		return CurrentDB.currentDB.getBuildingById(id);
	}
	
	public static Rental getRentalById(UUID id)
	{
		return CurrentDB.currentDB.getRentalById(id);
	}
	
	public static void saveTerm(Term term)
	{
		CurrentDB.currentDB.saveTerm(term);
	}
	
	public static void saveStudent(Student student)
	{
		CurrentDB.currentDB.saveStudent(student);
	}
	
	public static void saveLocker(Locker locker)
	{
		CurrentDB.currentDB.saveLocker(locker);
	}
	
	public static void saveBuilding(Building building)
	{
		CurrentDB.currentDB.saveBuilding(building);
	}
	
	public static void saveRental(Rental rental)
	{
		CurrentDB.currentDB.saveRental(rental);
	}
	
	public static ArrayList<Term> getAllTerms()
	{
		return CurrentDB.currentDB.getAllTerms();
	}
	
	public static ArrayList<Building> getAllBuildings()
	{
		return CurrentDB.currentDB.getAllBuildings();
	}
	
	public static ArrayList<Student> getStudentsListByTerm(UUID term)
	{
		return CurrentDB.currentDB.getStudentsListByTerm(term);
	}
	
	public static ArrayList<Locker> getLockersListByTerm(UUID term)
	{
		return CurrentDB.currentDB.getLockersListByTerm(term);
	}
	
	public static ArrayList<Rental> getRentalsListByTerm(UUID term)
	{
		return CurrentDB.currentDB.getRentalsListByTerm(term);
	}
	
	public static Student getStudentByNumberAndTerm(int number, UUID term)
	{
		return CurrentDB.currentDB.getStudentByNumberAndTerm(number, term);
	}
	
	public static Locker getLockerByNumberBuildingAndTerm(int number, UUID building, UUID term)
	{
		return CurrentDB.currentDB.getLockerByNumberBuildingAndTerm(number, building, term);
	}
	
	public static Rental getRentalByLocker(UUID locker)
	{
		return CurrentDB.currentDB.getRentalByLocker(locker);
	}
	
	public static Rental getRentalByStudent(UUID student)
	{
		return CurrentDB.currentDB.getRentalByStudent(student);
	}
	
	public static ArrayList<Locker> getFreeLockersForBuildingAndTerm(UUID building, UUID term)
	{
		return CurrentDB.currentDB.getFreeLockersForBuildingAndTerm(building, term);
	}
}
