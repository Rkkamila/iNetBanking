package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig 

{
	Properties prop;
	
	public ReadConfig()
	
	{
		File src =new File("./Configuration/config.properties");
		
		try 
		{
			FileInputStream fis = new FileInputStream(src); 
			prop = new Properties();
			prop.load(fis);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception is : "+e.getMessage());
		}
	}
	
	//Method for read value from Properties file
	
	public String getAppicationURL()
	{
		String url =prop.getProperty("baseURL");
		return url;
	}
	
	public String getUsername()
	{
		String UName =prop.getProperty("username");
		return UName;
	}
	
	public String getPassword()
	{
		String pwd =prop.getProperty("password");
		return pwd;
	}

}
