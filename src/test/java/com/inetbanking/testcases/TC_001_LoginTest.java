package com.inetbanking.testcases;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pageObject.LoginPage;

public class TC_001_LoginTest extends BaseClass 
{
	@Test
	public void loginTest() throws InterruptedException, IOException

	{

		
		logger.info("URL is opening");
		
		//Thread.sleep(5000);
		driver.manage().window().maximize();
		LoginPage lp = new LoginPage(driver);
		lp.SetUserName(username);
		
		logger.info("Entered Username");
		
		lp.SetPassword(password);
		
		logger.info("Entered Password");
		
		lp.ClickSubmit();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		//if(driver.getTitle().equals("Guru99 Bank Manager HomePage12")) // Failed Conditions
		{
			Assert.assertTrue(true);
			logger.info("Login successfully");
		}
		
		else
		{
			captureScreenshot(driver,"loginTest");
			Assert.assertTrue(false);			
			logger.info("Login Failed");
		}
	}
}
