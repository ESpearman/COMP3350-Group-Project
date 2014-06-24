package lms.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import lms.business.Building;
import lms.business.Locker;
import lms.business.Rental;
import lms.business.Student;
import lms.business.Term;

public class HSQLDBImpl implements IDB
{

	@Override
	public Term getTermById(UUID id, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		Term result = null;
		
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Term WHERE id='" + id.toString() + "';");
			if(results.next())
			{
				result = Term.parse(results);
			}
			results.close();
			statement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		releaseConnection(currentConnection);
		
		return result;
	}

	@Override
	public Student getStudentById(UUID id, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		Student result = null;
		
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Student WHERE id='" + id.toString() + "';");
			if(results.next())
			{
				result = Student.parse(results);
			}
			results.close();
			statement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		releaseConnection(currentConnection);
		
		return result;
		
	}

	@Override
	public Locker getLockerById(UUID id, Connection... conn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Building getBuildingById(UUID id, Connection... conn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rental getRentalById(UUID id, Connection... conn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTerm(Term term, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Term WHERE id='" + term.getId().toString() + "';");
			
			if(results.next())
			{
				Statement updateStatement = currentConnection.c.createStatement();
				updateStatement.executeUpdate("UPDATE Term SET name='" + term.getName() + "' WHERE id=" + term.getId().toString() + "';");
				updateStatement.close();
			}
			else
			{
				Statement insertStatement = currentConnection.c.createStatement();
				insertStatement.executeUpdate("INSERT INTO Term VALUES ('" + term.getId().toString() + "', '" + term.getName() + "');");
				insertStatement.close();
			}
			
			results.close();
			statement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		releaseConnection(currentConnection);
	}

	@Override
	public void saveStudent(Student student, Connection... conn)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveLocker(Locker locker, Connection... conn)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveBuilding(Building building, Connection... conn)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveRental(Rental rental, Connection... conn)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Term> getAllTerms(Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		ArrayList<Term> result = new ArrayList<Term>();
		
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Term;");
			while(results.next())
			{
				result.add(Term.parse(results));
			}
			results.close();
			statement.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		releaseConnection(currentConnection);
		
		return result;
	}

	@Override
	public ArrayList<Building> getAllBuildings(Connection... conn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Student> getStudentsListByTerm(UUID term,
			Connection... conn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Locker> getLockersListByTerm(UUID term, Connection... conn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Rental> getRentalsListByTerm(UUID term, Connection... conn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentByNumberAndTerm(int number, UUID term,
			Connection... conn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locker getLockerByNumberBuildingAndTerm(int number, UUID building,
			UUID term, Connection... conn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rental getRentalByLocker(UUID locker, Connection... conn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rental getRentalByStudent(UUID student, Connection... conn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Locker> getFreeLockersForBuildingAndTerm(UUID building,
			UUID term, Connection... conn)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private ConnectionData getConnection(Connection[] conn)
	{
		if(conn.length > 0)
		{
			return new ConnectionData(conn[0], false);
		}
		else
		{
			return new ConnectionData(ConnectionPool.getConnection(), true);
		}
	}
	
	private void releaseConnection(ConnectionData connectionData)
	{
		if(connectionData.shouldRelease)
		{
			ConnectionPool.releaseConnection(connectionData.c);
		}
	}
	
	private class ConnectionData
	{
		public ConnectionData(Connection c, boolean shouldRelease)
		{
			this.c = c;
			this.shouldRelease = shouldRelease;
		}
		
		public Connection c;
		public boolean shouldRelease;
	}

}
