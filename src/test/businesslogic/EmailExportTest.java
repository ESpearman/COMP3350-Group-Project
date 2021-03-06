package test.businesslogic;

import java.util.UUID;

import junit.framework.TestCase;
import lms.businesslogic.CurrentTermInfo;
import lms.businesslogic.EmailExport;
import lms.domainobjects.Rental;
import lms.domainobjects.Student;
import lms.domainobjects.Term;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;

public class EmailExportTest extends TestCase
{
	Student student;
	Rental rental;
	String result;
	
	protected void setUp()
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, true);
		Term currentTerm = new Term("Summer 2014");
		currentTerm.save();
		CurrentTermInfo.currentTerm = currentTerm;
	}
	
	public void testEmptyStudents()
	{
		String result = EmailExport.exportStudentEmails();
		
		assertEquals("No students currently registered", result);
	}
	
	public void testOneStudent()
	{
		UUID studentUUID = UUID.randomUUID();
		UUID filler = UUID.randomUUID();
		
		student = new Student(studentUUID, "Al", "Adams", "aaa@hotmail.com", 7640001, true, CurrentTermInfo.currentTerm.getId());
		student.save();
		
		rental = new Rental(CurrentTermInfo.currentTerm.getId(), studentUUID, filler, 1.0f, true);
		rental.save();
		
		result = EmailExport.exportStudentEmails();
		
		assertEquals("aaa@hotmail.com;", result);
	}
	
	public void testMultipleStudents()
	{
		UUID studentUUID = UUID.randomUUID();
		UUID filler = UUID.randomUUID();
		
		student = new Student(studentUUID, "Al", "Adams", "aaa@hotmail.com", 7640001, true, CurrentTermInfo.currentTerm.getId());
		student.save();
		rental = new Rental(CurrentTermInfo.currentTerm.getId(), studentUUID, filler, 1.0f, true);
		rental.save();
		
		studentUUID = UUID.randomUUID();
		
		student = new Student(studentUUID, "Billy", "Bob", "bbb@umanitoba.com", 7640002, true, CurrentTermInfo.currentTerm.getId());
		student.save();
		rental = new Rental(CurrentTermInfo.currentTerm.getId(), studentUUID, filler, 1.0f, true);
		rental.save();
		
		studentUUID = UUID.randomUUID();
		
		student = new Student(studentUUID, "Clark", "C", "ccc@cc.umanitoba.com", 7640001, true, CurrentTermInfo.currentTerm.getId());
		student.save();
		rental = new Rental(CurrentTermInfo.currentTerm.getId(), studentUUID, filler, 1.0f, true);
		rental.save();
		
		String result = EmailExport.exportStudentEmails();
		
		assertEquals("aaa@hotmail.com;bbb@umanitoba.com;ccc@cc.umanitoba.com;", result);
		
	}
	
	public void testSymbols()
	{
		UUID studentUUID = UUID.randomUUID();
		UUID filler = UUID.randomUUID();
		
		student = new Student(studentUUID, "Al", "Adams", "!04.32.7\".g;;;$&&\"@cc.umanitoba.ca", 7640001, true, CurrentTermInfo.currentTerm.getId());
		student.save();
		
		rental = new Rental(CurrentTermInfo.currentTerm.getId(), studentUUID, filler, 1.0f, true);
		rental.save();
		
		String result = EmailExport.exportStudentEmails();
		
		assertEquals("!04.32.7\".g;;;$&&\"@cc.umanitoba.ca;", result);
	}

}
