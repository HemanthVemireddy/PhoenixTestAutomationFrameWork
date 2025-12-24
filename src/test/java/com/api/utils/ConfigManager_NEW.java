package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager_NEW {
	private static Properties prop = new Properties(); // load the property file using load
	private static String path = "config/config.properties";

	private ConfigManager_NEW() {
		// private constructor
	}

	static {
		String env = System.getProperty("env");
		if (env == null || env.isBlank()) {
	        env = "qa"; // default environment
	    }
		env = env.toLowerCase().trim();
		switch(env)
		{
			case "dev" :
			{
			   path = "config/config.dev.properties";
			   break;
			}
			case "qa" :
			{
				path = "config/config.qa.properties";
				break;
			}
			case "uat" :
			{
				path = "config/config.uat.properties";
				break;
			}
			default :
			{
				path = "config/config.qa.properties";
			}
		}
		// operation of loading properties file and it will load once at the time of
		// class loading
		InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (file == null) 
		{
			throw new RuntimeException("can not found the file at the path " + file);
		} else 
		      {

				try 
				{
					prop.load(file);
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
	}

	public static String getproperty(String Key) {
		System.out.println(prop.getProperty(Key));
		return prop.getProperty(Key);
	}
}