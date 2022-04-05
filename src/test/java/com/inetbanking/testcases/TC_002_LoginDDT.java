package com.inetbanking.testcases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObject.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_002_LoginDDT extends BaseClass
{
    @Test(dataProvider="LoginData")
	public void loginDDT(String uname , String pwd)
	{
    	LoginPage lp=new LoginPage(driver);
    	lp.SetUserName(uname);
    	logger.info("User Name Entered as: "+uname);
    	lp.SetPassword(pwd);
    	logger.info("Password Entered as: "+pwd);
    	lp.ClickSubmit();
    	
    	if(isAlertPresent()==true)
    	{
    		driver.switchTo().alert().accept();
    		driver.switchTo().defaultContent();
    		Assert.assertTrue(false);
    		logger.warn("Invalid Login Credentials");
    	}
    	
    	else
    	{
    		Assert.assertTrue(true);
    		logger.info("Login Passesed");
    		lp.ClickLogout();
    		driver.switchTo().alert().accept();
    		driver.switchTo().defaultContent();
    	}
	}
    
    @DataProvider(name="LoginData")
    String [][] getData() throws IOException
    
    {
    	
    String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testdata/LoginData.xlsx";	
    
    //System.out.println("Data Provider Path: "+ path);
    
    int rownum=XLUtils.getRowCount(path,"Sheet1");
    int colcount=XLUtils.getCellCount(path,"Sheet1",1);
    
    logger.info("The total Num of Row :"+rownum+" and Total Num of Column is: "+colcount);
    
    String logindata [][]=new String [rownum][colcount];
    
    for(int i=1;i<=rownum;i++)
    {
    	for(int j=0;j<colcount;j++)
    	{
    		logindata [i-1][j]=XLUtils.getCellData(path,"Sheet1",i, j);
    	}
    	
    }
    return logindata;
    
    }
    
    public boolean isAlertPresent()  // Created to check Alert Present or Not
    {
    	try 
    	{
    		driver.switchTo().alert();
    		return true;
		} 
    	catch (NoAlertPresentException e) 
    	{
			return false;
		}
    }
    
}
