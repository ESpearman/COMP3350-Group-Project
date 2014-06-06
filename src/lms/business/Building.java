package lms.business;

import java.util.ArrayList;
import java.util.UUID;

import lms.db.DBProxy;
import lombok.Getter;

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
}
