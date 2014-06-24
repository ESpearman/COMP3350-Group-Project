package lms.business;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import lms.db.DBProxy;
import lombok.Getter;
import lombok.val;

public class Building implements BusinessObject
{
	@Getter
	private UUID id;
	
	@Getter
	private String name;
	
	public Building(UUID id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public Building(String name)
	{
		this(UUID.randomUUID(), name);
	}

	@Override
	public void save()
	{
		DBProxy.saveBuilding(this);
	}
	
	@Override
	public BusinessObject clone()
	{
		return new Building(id, name);
	}
	
	public static Building getById(UUID id)
	{
		return DBProxy.getBuildingById(id);
	}
	
	public static ArrayList<Building> getAll()
	{
		return DBProxy.getAllBuildings();
	}
	
	public static Building parse(ResultSet result)
	{
		try
		{
			val id = result.getString("id");
			val name = result.getString("name");
			
			val idUUID = UUID.fromString(id);
			
			return new Building(idUUID, name);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
