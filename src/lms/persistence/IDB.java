package lms.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.UUID;

import lms.domainobjects.Building;
import lms.domainobjects.Locker;
import lms.domainobjects.Rental;
import lms.domainobjects.Student;
import lms.domainobjects.Term;

public interface IDB
{
	void resetDB(Connection... conn);
	
	Term getTermById(UUID id, Connection ... conn);
	Student getStudentById(UUID id, Connection ... conn);
	Locker getLockerById(UUID id, Connection ... conn);
	Building getBuildingById(UUID id, Connection ... conn);
	Rental getRentalById(UUID id, Connection ... conn);
	
	void saveTerm(Term term, Connection ... conn);
	void saveStudent(Student student, Connection ... conn);
	void saveLocker(Locker locker, Connection ... conn);
	void saveBuilding(Building building, Connection ... conn);
	void saveRental(Rental rental, Connection ... conn);
	
	ArrayList<Term> getAllTerms(Connection ... conn);
	ArrayList<Building> getAllBuildings(Connection ... conn);
	
	ArrayList<Student> getStudentsListByTerm(UUID term, Connection ... conn);
	ArrayList<Locker> getLockersListByTerm(UUID term, Connection ... conn);
	ArrayList<Rental> getRentalsListByTerm(UUID term, Connection ... conn);
	
	Student getStudentByNumberAndTerm(int number, UUID term, Connection ... conn);
	Locker getLockerByNumberBuildingAndTerm(int number, UUID building, UUID term, Connection ... conn);
	Rental getRentalByLocker(UUID locker, Connection ... conn);
	Rental getRentalByStudent(UUID student, Connection ... conn);
	
	ArrayList<Locker> getFreeLockersForBuildingAndTerm(UUID building, UUID term, Connection ... conn);
}
