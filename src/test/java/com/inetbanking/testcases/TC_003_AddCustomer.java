package com.inetbanking.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObject.AddCustomerPage;
import com.inetbanking.pageObject.LoginPage;


public class TC_003_AddCustomer extends BaseClass
{
	@Test
	public void	addNewCustomer() throws InterruptedException, IOException
	{
		

		LoginPage lp = new LoginPage(driver);
		logger.info("Start Login for Add Cust TC");
		lp.SetUserName(username);
		
		logger.info("Entered Username");		
		lp.SetPassword(password);		
		logger.info("Entered Password");		
		lp.ClickSubmit();		
		Thread.sleep(5000);
		AddCustomerPage addcust = new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		Thread.sleep(5000);
		logger.info("Providing Customer Details...");
		
		String RandomName=RandomName();
		addcust.custNames(RandomName);
		logger.info("Entered Customer Name");
		
		addcust.custGender("male");
		logger.info("Selected Customer Gender");
		
		addcust.custdob("10", "09", "1995");
		logger.info("Entered Customer DOB");
		
		Thread.sleep(3000);
		addcust.custaddress("India");
		logger.info("Entered Customer Address");
		
		addcust.custcity("Bhubaneswar");
		logger.info("Entered Customer City");
		
		addcust.custstate("Odisha");
		logger.info("Entered Customer State");
		
		addcust.custpinno("754145");
		logger.info("Entered Customer Pin Number");
		
		String DmobNo=randomNumber();
		
		addcust.custtelephone(DmobNo);
		logger.info("Entered Customer Telephone Number");
		
		String Demail=RandomString()+"@gmail.com";
		addcust.custemail(Demail);
		
		addcust.custpassword("Rkkamila@#123");
		logger.info("Entered Customer Password");
		
		addcust.custsubmit();
		Thread.sleep(3000);
		
		logger.info("Validation Started...");
		boolean results=driver.getPageSource().contains("Customer Registered Successfully!!!"); 
		
		if(results==true)
		{
			Assert.assertTrue(true);
			logger.info("Test Case Passed");
		}
		
		else
		{
			captureScreenshot(driver,"addNewCustomer");
			Assert.assertTrue(false);
			logger.info("Test Case Failed");
			
		}
		
	}


}
