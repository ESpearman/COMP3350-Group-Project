package lms.business;

import java.util.ArrayList;
import java.util.UUID;

public interface BusinessObject<T>
{
	public T getById(UUID id);
	public ArrayList<T> getListByTerm(UUID id);
	public void save();
}
