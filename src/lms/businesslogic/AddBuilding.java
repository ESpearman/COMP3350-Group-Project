package lms.businesslogic;
import lms.domainobjects.Building;

public class AddBuilding 
{
	public static Building insert(String buildingName)
	{
		Building newBuilding = new Building (buildingName);
		newBuilding.save();
		return newBuilding; 
	}
}
