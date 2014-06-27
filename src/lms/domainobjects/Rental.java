package lms.domainobjects;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import lms.persistence.DBProxy;
import lombok.Getter;
import lombok.val;

public class Rental implements TermBased
{
	@Getter
	private UUID id;
	
	@Getter
	private UUID term;
	
	@Getter
	private UUID student;
	
	@Getter
	private UUID locker;
	
	@Getter
	private double pricePaid;
	
	@Getter
	private boolean signedAgreement;
	
	public Rental(UUID id, UUID term, UUID student, UUID locker, double price, boolean signedAgreement)
	{
		this.id = id;
		this.term = term;
		this.student = student;
		this.locker = locker;
		this.pricePaid = price;
		this.signedAgreement = signedAgreement;
	}
	
	public Rental(UUID term, UUID student, UUID locker, double price, boolean signedAgreement)
	{
		this(UUID.randomUUID(), term, student, locker, price, signedAgreement);
	}

	@Override
	public void save()
	{
		DBProxy.instance.saveRental(this);
	}
	
	public static Rental getById(UUID id)
	{
		return DBProxy.instance.getRentalById(id);
	}
	
	public static ArrayList<Rental> getListByTerm(UUID term)
	{
		return DBProxy.instance.getRentalsListByTerm(term);
	}
	
	public static Rental getByLocker(UUID locker)
	{
		return DBProxy.instance.getRentalByLocker(locker);
	}
	
	public static Rental getByStudent(UUID student)
	{
		return DBProxy.instance.getRentalByStudent(student);
	}
	
	@Override
	public BusinessObject clone()
	{
		return new Rental(id, term, student, locker, pricePaid, signedAgreement);
	}
	
	public static Rental parse(ResultSet result)
	{
		try
		{
			val id = result.getString("id");
			val term = result.getString("term");
			val student = result.getString("student");
			val locker = result.getString("locker");
			val price = result.getFloat("price_paid");
			val agreement = result.getBoolean("signed_agreement");
			
			val idUUID = UUID.fromString(id);
			val termUUID = UUID.fromString(term);
			val studentUUID = UUID.fromString(student);
			val lockerUUID = UUID.fromString(locker);
			
			return new Rental(idUUID, termUUID, studentUUID, lockerUUID, price, agreement);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
