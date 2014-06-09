package lms.business;

import java.util.ArrayList;
import java.util.UUID;

import lms.db.DBProxy;
import lombok.Getter;
import lombok.Setter;

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
		DBProxy.saveLocker(this);
	}

	public static Locker getById(UUID id)
	{
		return DBProxy.getLockerById(id);
	}
	
	public static ArrayList<Locker> getListByterm(UUID term)
	{
		return DBProxy.getLockersListByTerm(term);
	}
	
	public static Locker getByNumber(int number)
	{
		return DBProxy.getLockerByNumber(number);
	}
	
	@Override
	public BusinessObject clone()
	{
		return new Locker(id, term, number, building, size);
	}

}
