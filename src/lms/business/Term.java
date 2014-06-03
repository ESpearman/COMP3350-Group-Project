package lms.business;

import java.util.ArrayList;
import java.util.UUID;

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
	
	static public Term getById(UUID id)
	{
		return null;
	}
	
	public static ArrayList<Term> getAll()
	{
		return null;
	}
	
	@Override
	public void save()
	{
		
	}

	
}
