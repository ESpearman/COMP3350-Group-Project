package test.businesslogic;

import junit.framework.TestCase;
import lms.businesslogic.AddTerm;
import lms.domainobjects.Term;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;

public class AddTermTest extends TestCase
{	
	Term newTerm;
	
	protected void setUp() throws Exception
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, true);
	}
	
	public void testAddition()
	{
		newTerm = AddTerm.addTerm("new term");
		assertEquals(newTerm.getName() + " should equal \"new term\"", newTerm.getName(), "new term");
	}
}
