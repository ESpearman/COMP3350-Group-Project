package test.businesslogic;

import junit.framework.TestCase;
import java.util.ArrayList;

import lms.businesslogic.RentLocker;
import lms.domainobjects.Building;
import lms.domainobjects.Locker;
import lms.domainobjects.LockerSize;
import lms.domainobjects.Student;
import lms.domainobjects.Term;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;

public class RentLockerTest extends TestCase
{
	double price;
	Term term;
	Term term1;
	Term term2;
	Term failedTerm1;
	Term failedTerm2;
	ArrayList<Term> terms = new ArrayList<Term>();
	ArrayList<Term> multipleTerms = new ArrayList<Term>();
	ArrayList<Term> failedMultipleTerms = new ArrayList<Term>();
	Building building;
	Student validStudent;
	Student rentingStudent;
	Student lockerFirstStudent;
	Student lockerSecondStudent;
	Student multipleTermStudent;
	Student failedMultipleTermStudent;
	Locker locker;
	Locker rentedLocker;
	Locker secondLocker;
	Locker termOneLocker;
	Locker termTwoLocker;
	Locker thirdTestLocker;
	Locker failedTerm1Locker;
	Locker failedTerm2Locker;
	String validRental;
	String failedToRent;
	
	protected void setUp() throws Exception
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, true);
		price = 20;
		term = new Term("Test Term");
		terms.add(term);
		building = new Building("Test Building");
		
		//testValid
		validStudent = new Student("morgan", "epp", "mrgnepp@gmail.com", 9876543, true, term.getId());
		locker = new Locker(term.getId(), 101, building.getId(), LockerSize.FULL);
		
		//testStudentAlreadyRenting
		rentingStudent = new Student("carl", "sagan", "csagan@gmail.com", 1726354, true, term.getId());
		rentedLocker = new Locker(term.getId(), 666, building.getId(), LockerSize.FULL);
		secondLocker = new Locker(term.getId(), 567, building.getId(), LockerSize.FULL);
		
		//testLockerAlreadyRented
		thirdTestLocker = new Locker(term.getId(), 123, building.getId(), LockerSize.FULL);
		lockerFirstStudent = new Student("tayler", "fred", "taylerfred@gmail.com", 3214567, true, term.getId());
		lockerSecondStudent = new Student("billal", "kohistani", "billal@gmail.com", 1029384, true, term.getId());
		
		//testValidMultipleTerms
		term1 = new Term("Term 1");
		term2 = new Term("Term 2");
		multipleTerms.add(term1);
		multipleTerms.add(term2);
		termOneLocker = new Locker(term1.getId(), 999, building.getId(), LockerSize.FULL);
		termTwoLocker = new Locker(term2.getId(), 999, building.getId(), LockerSize.FULL);
		multipleTermStudent = new Student("Abraham", "Lincoln", "ak47@gmail.com", 9182735, true, term1.getId());
		
		//testFailedMultipleTerms
		failedTerm1 = new Term("failed Term1");
		failedTerm2 = new Term("failed Term2");
		failedMultipleTerms.add(failedTerm2);
		failedTerm1Locker = new Locker(failedTerm1.getId(), 987, building.getId(), LockerSize.FULL);
		failedTerm2Locker = new Locker(failedTerm2.getId(), 987, building.getId(), LockerSize.FULL);
		failedMultipleTermStudent = new Student("Bruh", "Pls", "Bruhpls@gmail.com", 5647385, true, term1.getId());
		
		term.save();
		building.save();
		validStudent.save();
		locker.save();
		
		rentingStudent.save();
		rentedLocker.save();
		secondLocker.save();
		
		thirdTestLocker.save();
		lockerFirstStudent.save();
		lockerSecondStudent.save();
		
		term1.save();
		term2.save();
		termOneLocker.save();
		termTwoLocker.save();
		multipleTermStudent.save();
		
		failedTerm1.save();
		failedTerm2.save();
		failedTerm1Locker.save();
		failedTerm2Locker.save();
		failedMultipleTermStudent.save();
	}
	
	// Student tries to rent 1 locker in a term
	public void testValid()
	{
		validRental = RentLocker.rent(validStudent, locker, terms, price);
		assertEquals(validRental, validRental, "");
	}
	
	// One student tries to rent multiple lockers in the same term
	public void testStudentAlreadyRenting()
	{
		validRental = RentLocker.rent(rentingStudent, rentedLocker, terms, price);
		failedToRent = RentLocker.rent(rentingStudent, secondLocker, terms, price);
		assertEquals("Shouldn't be able to rent, student already renting a locker this term", failedToRent, "The student is already renting a locker for the term: " + term.getName() + "\n");
	}
	
	// Two students try to rent the same locker
	public void testLockerAlreadyRented()
	{
		validRental = RentLocker.rent(lockerFirstStudent, thirdTestLocker, terms, price);
		failedToRent = RentLocker.rent(lockerSecondStudent, thirdTestLocker, terms, price);
		assertEquals("Locker should already be rented", failedToRent,"The locker is already rented for the term: " + term.getName() + "\n");
	}
	
	// Student rents a locker across multiple terms
	public void testValidMultipleTerms()
	{
		validRental = RentLocker.rent(multipleTermStudent, termOneLocker, multipleTerms, price);
		assertEquals("Should be able to rent across terms", validRental, "");
	}
	
	// Student tries to rent a locker that is rented in a different term
	public void testFailedMultipleTerms()
	{
		validRental = RentLocker.rent(multipleTermStudent, failedTerm2Locker, failedMultipleTerms, price);
		failedMultipleTerms.add(failedTerm1);
		failedToRent = RentLocker.rent(failedMultipleTermStudent, failedTerm1Locker, failedMultipleTerms, price);
		assertEquals("Should not have been able to rent, as 1 locker was already rented", failedToRent, "The locker is already rented for the term: " + failedTerm2.getName() + "\n");
	}
}
