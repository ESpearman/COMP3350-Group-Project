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
		ConnectionData currentConnection = getConnection(conn);
		Locker result = null;
		
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Locker WHERE id='" + id.toString() + "';");
			if(results.next())
			{
				result = Locker.parse(results);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		releaseConnection(currentConnection);
		return result;
	}

	@Override
	public Building getBuildingById(UUID id, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		Building result = null;
		
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Building WHERE id='" + id.toString() + "';");
			if(results.next())
			{
				result = Building.parse(results);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		releaseConnection(currentConnection);
		return result;
	}

	@Override
	public Rental getRentalById(UUID id, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		Rental result = null;
		
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Rental WHERE id='" + id.toString() + "';");
			if(results.next())
			{
				result = Rental.parse(results);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		releaseConnection(currentConnection);
		return result;
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
				updateStatement.executeUpdate("UPDATE Term SET name='" + term.getName()+ "' WHERE id=" + term.getId().toString() + "';");
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
		ConnectionData currentConnection = getConnection(conn);
		
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Student WHERE id='" + student.getId().toString() + "';");
			
			if(results.next())
			{
				Statement updateStatement = currentConnection.c.createStatement();
				updateStatement.executeUpdate(
						  "UPDATE Student "
						+ "SET first_name='" + student.getFirstName() + "', last_name='" + student.getLastName()
							+ "', email='" + student.getEmail() + "', student_number=" + student.getStudentNumber()
							+ ", science_student=" + student.isScienceStudent() + ", term='" + student.getTerm().toString()
						+ "' WHERE id=" + student.getId().toString() + "';");
				updateStatement.close();
			}
			else
			{
				Statement insertStatement = currentConnection.c.createStatement();
				insertStatement.executeUpdate("INSERT INTO Student VALUES ('" + student.getId().toString() + "', '" + student.getFirstName() + "', '" 
						+ student.getLastName() + "', '" + student.getEmail() + "', '" + student.getStudentNumber() + "', '" + student.isScienceStudent()
						+ "', '" + student.getTerm().toString() + "');");
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
	public void saveLocker(Locker locker, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Locker WHERE id='" + locker.getId().toString() + "';");
			
			if(results.next())
			{
				Statement updateStatement = currentConnection.c.createStatement();
				updateStatement.executeUpdate("UPDATE Locker " + "SET term='" + locker.getTerm().toString() + "', num=" + locker.getNumber()
						+ ", building='" + locker.getBuilding().toString() + "', locker_size='" + locker.getSizeString()
						+ "' WHERE id=" + locker.getId().toString() + "';");
				updateStatement.close();
			}
			else
			{
				Statement insertStatement = currentConnection.c.createStatement();
				insertStatement.executeUpdate("INSERT INTO Locker VALUES ('" + locker.getId().toString() + "', '" + locker.getTerm() + "', " 
						+ locker.getNumber() + ", '" + locker.getBuilding().toString() + "', '" + locker.getSizeString() + "');");
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
	public void saveBuilding(Building building, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Building WHERE id='" + building.getId().toString() + "';");
			
			if(results.next())
			{
				Statement updateStatement = currentConnection.c.createStatement();
				updateStatement.executeUpdate("UPDATE Building SET name='" + building.getName()+ "' WHERE id=" + building.getId().toString() + "';");
				updateStatement.close();
			}
			else
			{
				Statement insertStatement = currentConnection.c.createStatement();
				insertStatement.executeUpdate("INSERT INTO Building VALUES ('" + building.getId().toString() + "', '" + building.getName() + "');");
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
	public void saveRental(Rental rental, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Rental WHERE id='" + rental.getId().toString() + "';");
			
			if(results.next())
			{
				Statement updateStatement = currentConnection.c.createStatement();
				updateStatement.executeUpdate("UPDATE Rental SET term='" + rental.getTerm().toString() + "', student='" + rental.getStudent().toString()
						+ "', locker='" + rental.getLocker().toString() + "', price_paid=" + rental.getPricePaid()  
						+ "WHERE id=" + rental.getId().toString() + "';");
				updateStatement.close();
			}
			else
			{
				Statement insertStatement = currentConnection.c.createStatement();
				insertStatement.executeUpdate("INSERT INTO Rental VALUES ('" + rental.getId().toString() + "', '" + rental.getTerm().toString() 
						+ "', student='" + rental.getStudent().toString() + "', locker='" + rental.getLocker().toString()
						+ "', price_paid=" + rental.getPricePaid() + ", signed_agreement=" + rental.isSignedAgreement() + ");");
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
		ConnectionData currentConnection = getConnection(conn);
		ArrayList<Building> result = new ArrayList<Building>();
		
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Building;");
			while(results.next())
			{
				result.add(Building.parse(results));
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
	public ArrayList<Student> getStudentsListByTerm(UUID term,
			Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		ArrayList<Student> result = new ArrayList<Student>();
			
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Student WHERE term ='" + term + "';");
			while(results.next())
				{
					result.add(Student.parse(results));
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
	public ArrayList<Locker> getLockersListByTerm(UUID term, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		ArrayList<Locker> result = new ArrayList<Locker>();
			
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Locker WHERE term ='" + term + "';");
			while(results.next())
				{
					result.add(Locker.parse(results));
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
	public ArrayList<Rental> getRentalsListByTerm(UUID term, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		ArrayList<Rental> result = new ArrayList<Rental>();
			
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Rental WHERE term ='" + term + "';");
			while(results.next())
				{
					result.add(Rental.parse(results));
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
	public Student getStudentByNumberAndTerm(int number, UUID term,
			Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		Student result = null;
			
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Student WHERE term ='" + term + "' AND student_number=" + number + ";");
			while(results.next())
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
	public Locker getLockerByNumberBuildingAndTerm(int number, UUID building,
			UUID term, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		Locker result = null;
			
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Locker WHERE term='" + term + "' AND building='" + building + "';");
			while(results.next())
				{
					result = Locker.parse(results);
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
	public Rental getRentalByLocker(UUID locker, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		Rental result = null;
			
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Rental WHERE Locker='" + locker + "';");
			while(results.next())
				{
					result = Rental.parse(results);
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
	public Rental getRentalByStudent(UUID student, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		Rental result = null;
			
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Rental WHERE student='" + student + "';");
			while(results.next())
				{
					result = Rental.parse(results);
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
	public ArrayList<Locker> getFreeLockersForBuildingAndTerm(UUID building,
			UUID term, Connection... conn)
	{
		ConnectionData currentConnection = getConnection(conn);
		ArrayList<Locker> result = new ArrayList<Locker>();
			
		try
		{
			Statement statement = currentConnection.c.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM Locker WHERE term='" + term + "', building='" + building
					+ "' AND Locker.id NOT IN (SELECT locker FROM Rental);");
			while(results.next())
				{
					result.add(Locker.parse(results));
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
