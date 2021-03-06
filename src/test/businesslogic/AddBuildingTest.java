package test.businesslogic;

import java.util.UUID;

import junit.framework.TestCase;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;
import lms.businesslogic.AddBuilding;
import lms.domainobjects.Building;

public class AddBuildingTest extends TestCase
{
	UUID id = UUID.randomUUID();
	UUID term = UUID.randomUUID();
	
	Building newBuilding;
	Building testInsert;

	public void setUp() throws Exception
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, true);
		newBuilding = new Building("Drake");
		newBuilding.save();
	}
	
	public void testInsert()
	{
		testInsert = AddBuilding.insert("Fletcher");
		assertNotNull("insertBuilding() did not add new building, building is null", testInsert);
	}
	
	public void testDuplicateBuilding()
	{
		testInsert = AddBuilding.insert("duplicate");
		testInsert = AddBuilding.insert("duplicate");
		assertNull("testInsert should be null, as a building with that name already exists", testInsert);
	}

}
