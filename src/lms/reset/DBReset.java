package lms.reset;

import java.util.UUID;

import lms.businesslogic.CurrentTermInfo;
import lms.config.ConfigData;
import lms.domainobjects.Building;
import lms.domainobjects.Locker;
import lms.domainobjects.LockerSize;
import lms.domainobjects.Rental;
import lms.domainobjects.Student;
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
		
		Building building1 = new Building("Machray Hall");
		building1.save();
		Building building2 = new Building("Armes");
		building2.save();
		
		Locker locker1 = new Locker(CurrentTermInfo.currentTerm.getId(), 1, building1.getId(), LockerSize.FULL);
		locker1.save();
		
		Locker locker2 = new Locker(CurrentTermInfo.currentTerm.getId(), 2, building2.getId(), LockerSize.FULL);
		locker2.save();
		
		Student student1 = new Student("Some", "Dude", "1@1.com", 1000, true, CurrentTermInfo.currentTerm.getId());
		student1.save();
		
		Rental rental1 = new Rental(CurrentTermInfo.currentTerm.getId(), student1.getId(), locker1.getId(), 0.0, true);
		rental1.save();
		
		System.out.println("Completed Generating Demo Data");
		
		ConnectionPool.clearConnections();
		
		System.out.println("DB reset complete");
	}

}
