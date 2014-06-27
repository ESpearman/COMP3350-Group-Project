package lms.domainobjects;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import lms.persistence.DBProxy;
import lombok.Getter;
import lombok.val;

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
		DBProxy.instance.saveTerm(this);
	}
	
	public static Term getById(UUID id)
	{
		return DBProxy.instance.getTermById(id);
	}
	
	public static ArrayList<Term> getAll()
	{
		return DBProxy.instance.getAllTerms();
	}
	
	public static Term parse(ResultSet result)
	{
		try
		{
			val id = result.getString("id");
			val name = result.getString("name");
			
			val idUUID = UUID.fromString(id);
			
			return new Term(idUUID, name);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
