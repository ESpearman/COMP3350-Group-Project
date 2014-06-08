package lms.db;

import java.util.ArrayList;
import java.util.UUID;

import lms.business.Building;
import lms.business.Locker;
import lms.business.Rental;
import lms.business.Student;
import lms.business.Term;
import lms.stubdb.StubDB;

public class DBProxy
{
	//This class is only meant to be used as an interface to the database for business objects
	//Do not call these methods - DB queries should be made using the methods in the business objects
	
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
	
	public static Building getBuildingById(UUID id)
	{
		return StubDB.getBuildingById(id);
	}
	
	public static Rental getRentalById(UUID id)
	{
		return StubDB.getRentalById(id);
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
	
	public static void saveBuilding(Building building)
	{
		StubDB.saveBuilding(building);
	}
	
	public static void saveRental(Rental rental)
	{
		StubDB.saveRental(rental);
	}
	
	public static ArrayList<Term> getAllTerms()
	{
		return StubDB.getAllTerms();
	}
	
	public static ArrayList<Building> getAllBuildings()
	{
		return StubDB.getAllBuildings();
	}
	
	public static ArrayList<Student> getStudentsListByTerm(UUID term)
	{
		return StubDB.getStudentsListByTerm(term);
	}
	
	public static ArrayList<Locker> getLocerksListByTerm(UUID term)
	{
		return StubDB.getLockersListByTerm(term);
	}
	
	public static ArrayList<Rental> getRentalsListByTerm(UUID term)
	{
		return StubDB.getRentalsListByTerm(term);
	}
	
	public static Student getStudentByNumber(int number)
	{
		return StubDB.getStudentByNumber(number);
	}
	
	public static Locker getLockerByNumber(int number)
	{
		return StubDB.getLockerByNumber(number);
	}
	
	public static Rental getRentalByLocker(UUID locker)
	{
		return StubDB.getRentalByLocker(locker);
	}
	
	public static Rental getRentalByStudent(UUID student)
	{
		return StubDB.getRentalByStudent(student);
	}
}
