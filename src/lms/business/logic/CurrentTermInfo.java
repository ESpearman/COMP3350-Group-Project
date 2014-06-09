package lms.business.logic;

import java.util.UUID;

public class CurrentTermInfo 
{
	private static UUID id;
	private static String name;
	
	public static UUID getId() {
		return id;
	}
	public static void setId(UUID id) {
		CurrentTermInfo.id = id;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		CurrentTermInfo.name = name;
	}
	
}
