package lms.config;

import java.io.File;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigData
{
	public static String dbURL;
	public static String dbUsername;
	public static String dbPassword;
	
	public static double fullScience;
	public static double fullNonScience;
	
	public static double halfScience;
	public static double halfNonScience; 
	
	public static void init()
	{
		File f = new File("conf/application.conf");
		Config config = ConfigFactory.parseFile(f);
		
		dbURL = config.getString("dburl");
		dbUsername = config.getString("dbusername");
		dbPassword = config.getString("dbpassword");
		
		fullScience = config.getDouble("fullScience"); 
		fullNonScience = config.getDouble("fullNonScience");
		
		halfScience = config.getDouble("halfScience");
		halfNonScience = config.getDouble("halfNonScience");
						
	}
}
