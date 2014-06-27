package test.business.logic;

import java.util.UUID;

import junit.framework.TestCase;
import lms.business.Locker;
import lms.business.LockerSize;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;
import lms.business.logic.AddLocker;

public class AddLockerTest extends TestCase
{
	UUID id = UUID.randomUUID();
	UUID term = UUID.randomUUID();
	UUID building = UUID.randomUUID();
	
	LockerSize size = LockerSize.FULL; 
	Locker newLocker;
	Locker testInsert;

	
	public void setUp() throws Exception
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, true);
		newLocker = new Locker(id, term, 007, building, size);
		newLocker.save();
	}
	public void testInsert()
	{
		testInsert = AddLocker.insert(term, "001", building, size);
		assertNotNull("insertLocker() did not add new locker, locker is null", testInsert);
	}

}
