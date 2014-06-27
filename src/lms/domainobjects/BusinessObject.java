package lms.domainobjects;

import java.util.UUID;

public interface BusinessObject
{
	UUID getId();
	void save();
	BusinessObject clone();
}
