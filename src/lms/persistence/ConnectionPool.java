package lms.persistence;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lms.config.ConfigData;

public class ConnectionPool
{
	private static ArrayList<Connection> connections;
	private static ArrayList<Boolean> locks;
	
	static
	{
		connections = new ArrayList<Connection>();
		locks = new ArrayList<Boolean>();
	}
	
	public static void init(int numConnections)
	{
		connections.clear();
		locks.clear();
		
		for(int i = 0; i < numConnections; i++)
		{
			connections.add(createConnection());
			locks.add(new Boolean(false));
		}
	}
	
	public static void clearConnections()
	{
		for(Connection conn: connections)
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		connections.clear();
		locks.clear();
	}
	
	public static Connection getConnection()
	{
		for(int i = 0; i < connections.size(); i++)
		{
			if(! locks.get(i).booleanValue())
			{
				locks.set(i, new Boolean(true));
				return connections.get(i);
			}
		}
		
		return null;
	}
	
	public static Connection getTransaction()
	{
		Connection result = getConnection();
		
		try
		{
			result.setAutoCommit(false);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void commitAndReleaseTransaction(Connection c)
	{
		try
		{
			c.commit();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		releaseConnection(c);
	}
	
	public static void discardAndReleaseTransaction(Connection c)
	{
		try
		{
			c.rollback();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void releaseConnection(Connection c)
	{
		try
		{
			c.setAutoCommit(true);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		for(int i = 0; i < connections.size(); i++)
		{
			if(connections.get(i) == c)
			{
				locks.set(i,  new Boolean(false));
			}
		}
	}
	
	private static Connection createConnection()
	{
		try
		{
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:" + ConfigData.dbURL, ConfigData.dbUsername, ConfigData.dbPassword);
			return c;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
