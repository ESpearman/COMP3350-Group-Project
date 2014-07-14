package lms.businesslogic;

import java.util.ArrayList;

import lms.domainobjects.Term;

public class AddTerm 
{
	public static Term addTerm(String inputTerm)
	{
		ArrayList<Term> currTerms = Term.getAll();
		for(Term currTerm : currTerms)
		{
			if(currTerm.getName().equals(inputTerm))
			{
				return null;
			}
		}

		Term term = new Term(inputTerm);
		term.save();
		return term;
	}
}
