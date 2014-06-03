package lms.business;

import java.util.ArrayList;
import java.util.UUID;

import lms.stubdb.StubDB;
import lombok.Getter;

public class Term implements BusinessObject
{
	@Getter
	private UUID id;
	
	@Getter
	private String name;
	
	public Term(String name)
	{
		this.id = UUID.randomUUID();
		this.name = name;
	}
	
	public Term(UUID id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	@Override
	public UUID getId()
	{
		return id;
	}
	
	@Override
	public void save()
	{
		StubDB.saveTerm(this);
	}
	
	public static Term getById(UUID id)
	{
		return StubDB.getTermById(id);
	}
	
	public static ArrayList<Term> getAll()
	{
		return null;
	}
	
	
}
