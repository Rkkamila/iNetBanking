package com.inetbanking.testcases;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
	ReadConfig readconfig =new ReadConfig();
	
	public String baseURL=readconfig.getAppicationURL();
	public String username =readconfig.getUsername();
	public String password =readconfig.getPassword();
	public static WebDriver driver;
	public Logger logger;
	
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br)
	
	{	
		logger = Logger.getLogger(BaseClass.class);
		BasicConfigurator.configure();
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome")) 
		{
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		opt.setExperimentalOption("excludeSwitches",Arrays.asList("disabele-popup-blocking") );
		driver = new ChromeDriver();
		logger.info("Chrome Driver Launched");
		}
		
		else if(br.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();	
			logger.info("Edge Driver Launched");
		}
	    //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000000));
		driver.get(baseURL);
		driver.manage().window().maximize();

	}
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
		logger.info("Broser Closed Successfully");
	}
	
	public void captureScreenshot(WebDriver driver , String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		assert target != null;
		System.out.println("The fileName is `" + target+"`");
		FileUtils.copyFile(source, target);
		logger.info("Screenshot Taken for Failure");	
		
	}
	
	
	public String RandomString()
	{
		String generatedStr=RandomStringUtils.randomAlphabetic(8);
		return generatedStr;	
	}
	
	public String randomNumber()
	{
		String generatedStr2=RandomStringUtils.randomNumeric(10);
		return generatedStr2;
	}
	
	public String RandomName()
	{
		String generatedStr3=RandomStringUtils.randomAlphabetic(8);
		return generatedStr3;	
	}
	


}
