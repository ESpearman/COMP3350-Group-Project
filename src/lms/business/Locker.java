package lms.business;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import lms.persistence.DBProxy;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

public class Locker implements TermBased
{
	@Getter
	private UUID id;
	
	@Getter
	private UUID term;
	
	@Getter
	private int number;
	
	@Getter
	private UUID building;
	
	@Getter
	@Setter
	private LockerSize size;

	public Locker(UUID id, UUID term, int number, UUID building, LockerSize size)
	{
		this.id = id;
		this.term = term;
		this.number = number;
		this.building = building;
		this.size = size;
	}
	
	public Locker(UUID term, int number, UUID building, LockerSize size)
	{
		this(UUID.randomUUID(), term, number, building, size);
	}

	@Override
	public void save()
	{
		DBProxy.instance.saveLocker(this);
	}

	public static Locker getById(UUID id)
	{
		return DBProxy.instance.getLockerById(id);
	}
	
	public static ArrayList<Locker> getListByTerm(UUID term)
	{
		return DBProxy.instance.getLockersListByTerm(term);
	}
	
	public static Locker getByNumberBuildingAndTerm(int number, UUID building, UUID term)
	{
		return DBProxy.instance.getLockerByNumberBuildingAndTerm(number, building, term);
	}
	
	public static ArrayList<Locker> getFreeByBuildingAndTerm(UUID building, UUID term)
	{
		return DBProxy.instance.getFreeLockersForBuildingAndTerm(building, term);
	}
	
	@Override
	public BusinessObject clone()
	{
		return new Locker(id, term, number, building, size);
	}
	
	public static Locker parse(ResultSet result)
	{
		try
		{
			val id = result.getString("id");
			val term = result.getString("term");
			val num = result.getInt("num");
			val building = result.getString("building");
			val size = result.getString("locker_size");
			
			val idUUID = UUID.fromString(id);
			val termUUID = UUID.fromString(term);
			val buildingUUID = UUID.fromString(building);
			val sizeEnum = LockerSize.valueOf(size);
			
			return new Locker(idUUID, termUUID, num, buildingUUID, sizeEnum);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getSizeString()
	{
		if(size == LockerSize.FULL)
		{
			return "FULL";
		}
		else
		{
			return "HALF";
		}
	}

}
