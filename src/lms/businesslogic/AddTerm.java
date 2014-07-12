package lms.businesslogic;

import lms.domainobjects.Term;

public class AddTerm 
{
	public static Term addTerm(String inputTerm)
	{
		// Should check if it exists in the DB
		Term term = new Term(inputTerm);
		term.save();
		return term;
	}
}
