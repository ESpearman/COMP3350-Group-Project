package lms.business;

import java.util.ArrayList;
import java.util.UUID;

import lms.db.DBProxy;
import lombok.Getter;

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
	private float pricePaid;
	
	@Getter
	private boolean signedAgreement;
	
	public Rental(UUID id, UUID term, UUID student, UUID locker, float pricePaid, boolean signedAgreement)
	{
		this.id = id;
		this.term = term;
		this.student = student;
		this.locker = locker;
		this.pricePaid = pricePaid;
		this.signedAgreement = signedAgreement;
	}
	
	public Rental(UUID term, UUID student, UUID locker, float pricePaid, boolean signedAgreement)
	{
		this(UUID.randomUUID(), term, student, locker, pricePaid, signedAgreement);
	}

	@Override
	public void save()
	{
		DBProxy.saveRental(this);
	}
	
	public static Rental getById(UUID id)
	{
		return DBProxy.getRentalById(id);
	}
	
	public static ArrayList<Rental> getListByTerm(UUID term)
	{
		return DBProxy.getRentalsListByTerm(term);
	}
	
	public static Rental getByLocker(UUID locker)
	{
		return DBProxy.getRentalByLocker(locker);
	}
	
	public static Rental getByStudent(UUID student)
	{
		return DBProxy.getRentalByStudent(student);
	}
	
	@Override
	public BusinessObject clone()
	{
		return new Rental(id, term, student, locker, pricePaid, signedAgreement);
	}
	
	
}
