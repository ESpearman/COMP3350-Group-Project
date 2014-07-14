package lms.businesslogic;

import lms.domainobjects.Building;
import lms.domainobjects.Locker;
import lms.domainobjects.LockerSize;
import lms.domainobjects.Rental;
import lms.domainobjects.Student;

public class DemoDataGenerator
{
	public static void generate()
	{
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
	}
}
