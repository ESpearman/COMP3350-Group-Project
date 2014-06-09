package lms.business.logic;

import lms.business.Building;
import lms.business.Locker;
import lms.business.LockerSize;

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
	}
}
