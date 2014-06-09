package lms.business.logic;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class CurrentTermInfo 
{
	@Getter
	@Setter
	private static UUID id;
	
	@Getter
	@Setter
	private static String name;
	
	public static void createCurrentTerm()
	{
		id = UUID.randomUUID();
		name = "default";
	}
}
