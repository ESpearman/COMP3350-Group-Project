package lms.reset;

import java.util.UUID;

import lms.businesslogic.CurrentTermInfo;
import lms.businesslogic.SpreadsheetImporter;
import lms.config.ConfigData;
import lms.domainobjects.Term;
import lms.persistence.ConnectionPool;
import lms.persistence.DBInjector;
import lms.persistence.DBProxy;

public class DBReset
{	

	public static void main(String[] args)
	{
		DBProxy.instance = new DBProxy();
		DBInjector.injectInto(DBProxy.instance, false);
		
		ConfigData.init();
		ConnectionPool.init(4);
		
		DBProxy.instance.resetDB();
		
		Term term = new Term("Demo Term");
		term.save();
		CurrentTermInfo.currentTerm = term;
		
		SpreadsheetImporter.importStudents("spreadsheets/Students1.xlsx");
		SpreadsheetImporter.importLockers("spreadsheets/Lockers1.xlsx");
		
		System.out.println("DB reset complete");
	}

}
