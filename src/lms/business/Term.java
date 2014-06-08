package lms.business;

import java.util.ArrayList;
import java.util.UUID;

import lms.db.DBProxy;
import lombok.Getter;

public class Term implements BusinessObject
{
	@Getter
	private UUID id;
	
	@Getter
	private String name;
	
	public Term(UUID id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public Term(String name)
	{
		this(UUID.randomUUID(), name);
	}
	
	@Override
	public BusinessObject clone()
	{
		return (BusinessObject)(new Term(id, name));
	}
	
	@Override
	public void save()
	{
		DBProxy.saveTerm(this);
	}
	
	public static Term getById(UUID id)
	{
		return DBProxy.getTermById(id);
	}
	
	public static ArrayList<Term> getAll()
	{
		return DBProxy.getAllTerms();
	}
	
	
}
