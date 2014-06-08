package lms.business;

class LockerPrice
{
	private final double SCI_HALF = 10;
	private final double NONSCI_HALF = 15;
	private final double SCI_FULL = 20;
	private final double NONSCI_FULL = 25;

	public boolean isScience;
	public String lockerSize;

	public LockerPrice(boolean isScience, String lockerSize)
	{
		this.isScience = isScience;
		this.lockerSize = lockerSize;
	}
	public double calculatePrice()
	{
		double cost = 0;

		if(isScience)
		{
			if(lockerSize == "FULL")
			{
				cost = SCI_FULL;
			}
			else
			{
				cost = SCI_HALF;
			}
		}
		else
		{
			if(lockerSize == "FULL")
			{
				cost = NONSCI_FULL ;
			}
			else
			{
				cost = NONSCI_HALF;
			}
		}
		return cost;
	}
}