package lms.businesslogic;
import java.util.ArrayList;

import lms.domainobjects.Building;

public class AddBuilding 
{
	public static Building insert(String buildingName)
	{
		ArrayList<Building> currBuildings = Building.getAll();
		for(Building currBuilding : currBuildings)
		{
			if(currBuilding.getName().equals(buildingName))
			{
				return null;
			}
		}
		Building newBuilding = new Building (buildingName);
		newBuilding.save();
		return newBuilding; 
	}
}
