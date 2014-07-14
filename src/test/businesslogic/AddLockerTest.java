package test.businesslogic;

import java.util.UUID;

import junit.framework.TestCase;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;
import lms.businesslogic.AddLocker;
import lms.domainobjects.Locker;
import lms.domainobjects.LockerSize;

public class AddLockerTest extends TestCase
{
	UUID id = UUID.randomUUID();
	UUID term = UUID.randomUUID();
	UUID building = UUID.randomUUID();
	
	LockerSize size = LockerSize.FULL; 
	Locker testInsert;

	
	public void setUp() throws Exception
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, true);
	}
	
	public void testInsert()
	{
		testInsert = AddLocker.insert(term, "001", building, size);
		assertNotNull("insertLocker() did not add new locker, locker is null", testInsert);
	}
	
	public void testDuplicateNumber()
	{
		testInsert = AddLocker.insert(term, "002", building, size);
		testInsert = AddLocker.insert(term, "002", building, size);
		assertNull("testInsert should be null as it's a duplicate locker number", testInsert);
	}

}
