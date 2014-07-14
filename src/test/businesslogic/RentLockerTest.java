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
	ArrayList<Term> terms = new ArrayList<Term>();
	Building building;
	Student student;
	Student secondStudent;
	Locker locker;
	Locker secondLocker;
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
		term.save();
		building.save();	
	}
	
	// Student tries to rent 1 locker in a term
	public void testValid()
	{
		//testValid
		student = new Student("morgan", "epp", "mrgnepp@gmail.com", 9876543, true, term.getId());
		locker = new Locker(term.getId(), 101, building.getId(), LockerSize.FULL);
		locker.save();		
		
		validRental = RentLocker.rent(student, locker, terms, price);
		assertEquals(validRental, validRental, "");
	}
	
	// One student tries to rent multiple lockers in the same term
	public void testStudentAlreadyRenting()
	{
		student = new Student("carl", "sagan", "csagan@gmail.com", 1726354, true, term.getId());
		locker = new Locker(term.getId(), 666, building.getId(), LockerSize.FULL);
		secondLocker = new Locker(term.getId(), 567, building.getId(), LockerSize.FULL);
		locker.save();
		secondLocker.save();
		
		validRental = RentLocker.rent(student, locker, terms, price);
		failedToRent = RentLocker.rent(student, secondLocker, terms, price);
		assertEquals("Shouldn't be able to rent, student already renting a locker this term", failedToRent, "The student is already renting a locker for the term: " + term.getName() + "\n");
	}
	
	// Two students try to rent the same locker
	public void testLockerAlreadyRented()
	{
		locker = new Locker(term.getId(), 123, building.getId(), LockerSize.FULL);
		student = new Student("tayler", "fred", "taylerfred@gmail.com", 3214567, true, term.getId());
		secondStudent = new Student("billal", "kohistani", "billal@gmail.com", 1029384, true, term.getId());
		locker.save();
		
		validRental = RentLocker.rent(student, locker, terms, price);
		failedToRent = RentLocker.rent(secondStudent, locker, terms, price);
		assertEquals("Locker should already be rented", failedToRent,"The locker is already rented for the term: " + term.getName() + "\n");
	}
	
	// Student rents a locker across multiple terms
	public void testValidMultipleTerms()
	{
		//testValidMultipleTerms
		Term term1 = new Term("Term 1");
		Term term2 = new Term("Term 2");
		ArrayList<Term> multipleTerms = new ArrayList<Term>();
		multipleTerms.add(term1);
		multipleTerms.add(term2);
		
		locker = new Locker(term1.getId(), 999, building.getId(), LockerSize.FULL);
		secondLocker = new Locker(term2.getId(), 999, building.getId(), LockerSize.FULL);
		student = new Student("Abraham", "Lincoln", "ak47@gmail.com", 9182735, true, term1.getId());
		locker.save();
		secondLocker.save();
				
		validRental = RentLocker.rent(student, locker, multipleTerms, price);
		assertEquals("Should be able to rent across terms", "", validRental);
	}
	
	// Student tries to rent a locker that is rented in a different term
	public void testFailedMultipleTerms()
	{
		Term failedTerm1 = new Term("failed Term1");
		Term failedTerm2 = new Term("failed Term2");
		ArrayList<Term> failedMultipleTerms = new ArrayList<Term>();
		failedMultipleTerms.add(failedTerm2);
		
		locker = new Locker(failedTerm1.getId(), 987, building.getId(), LockerSize.FULL);
		secondLocker = new Locker(failedTerm2.getId(), 987, building.getId(), LockerSize.FULL);
		secondStudent = new Student("Bruh", "Pls", "Bruhpls@gmail.com", 5647385, true, failedTerm1.getId());
		student = new Student("Abraham", "Lincoln", "ak47@gmail.com", 9182735, true, failedTerm2.getId());
		locker.save();
		secondLocker.save();
		
		validRental = RentLocker.rent(student, secondLocker, failedMultipleTerms, price);
		failedMultipleTerms.add(failedTerm1);
		failedToRent = RentLocker.rent(secondStudent, locker, failedMultipleTerms, price);
		assertEquals("Should not have been able to rent, as 1 locker was already rented", "The locker is already rented for the term: " + failedTerm2.getName() + "\n", failedToRent);
	}
}