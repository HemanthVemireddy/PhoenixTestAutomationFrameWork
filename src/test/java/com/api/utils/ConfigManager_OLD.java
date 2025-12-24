package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager_OLD {
	private ConfigManager_OLD()
	{
		
	}
	private static Properties prop = new Properties(); // load the property file using load
	static {
		// operation of loading properties file and it will load once at the time of
		// class loading
		String LOCAL_PATH = System.getProperty("user.dir");
		File file = new File(LOCAL_PATH + File.separator + "src" + File.separator +  "test" + File.separator + "resources" + File.separator + "config" + File.separator +  "config.properties");
		FileReader reader = null;
		try 
		{
			reader = new FileReader(file);
			prop.load(reader);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Create the object of property class
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getproperty(String Key) {
		System.out.println(prop.getProperty(Key));
		return prop.getProperty(Key);
	}
}