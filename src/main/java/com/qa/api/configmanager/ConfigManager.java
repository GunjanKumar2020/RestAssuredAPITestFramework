package com.qa.api.configmanager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private static Properties properties=new Properties();
	
	static
	{
		InputStream input=ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties");
		if(input!=null)
		{
			try {
				properties.load(input);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public static String get(String Key)
	{
		return properties.getProperty(Key);
	}
	
	public static void set(String Key, String value)
	{
		 properties.setProperty(Key, value);
	}

}
