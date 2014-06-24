package lms.persistence;


public class CurrentDB
{
	public static IDB currentDB;
	
	public static void init(boolean testing)
	{
		if(testing)
		{
			currentDB = new StubDBImpl();
		}
		else
		{
			try {
		        Class.forName("org.hsqldb.jdbcDriver" );
		    } catch (Exception e) {
		        System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
		        e.printStackTrace();
		        return;
		    }
			
			currentDB = new HSQLDBImpl();
		}
	}
}
