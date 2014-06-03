package lms.business;

import java.util.ArrayList;
import java.util.UUID;

import lombok.Getter;

public class Term implements BusinessObject<Term>
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
	
	@Override
	public Term getById(UUID id)
	{
		return null;
	}
	
	@Override
	public ArrayList<Term> getListByTerm(UUID id)
	{
		return null;
	}
	
	@Override
	public void save()
	{
		
	}

	
}
