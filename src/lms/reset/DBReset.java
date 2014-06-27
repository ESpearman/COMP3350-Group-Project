package lms.reset;

import java.util.UUID;

import lms.business.Term;
import lms.business.logic.CurrentTermInfo;
import lms.business.logic.SpreadsheetImporter;
import lms.config.ConfigData;
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
		
		Term term = new Term(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"),"Demo Term");
		CurrentTermInfo.currentTerm = term;
		term.save();
		
		SpreadsheetImporter.importStudents("spreadsheets/Students1.xlsx");
		SpreadsheetImporter.importLockers("spreadsheets/Lockers1.xlsx");
		
		System.out.println("DB reset complete");
	}

}
