package test.business.logic;

import java.util.UUID;

import junit.framework.TestCase;
import lms.business.Building;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;
import lms.business.logic.AddBuilding;

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

}
