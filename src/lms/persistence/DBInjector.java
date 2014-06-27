package lms.persistence;


public class DBInjector
{	
	public static void injectInto(DBInjectable toInject, boolean testing)
	{
		if(testing)
		{
			toInject.setCurrentDB(new StubDBImpl());
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
			
			toInject.setCurrentDB(new HSQLDBImpl());
		}
	}
}
