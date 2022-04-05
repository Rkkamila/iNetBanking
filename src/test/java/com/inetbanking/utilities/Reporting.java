package com.inetbanking.utilities;
//Listener Class used to generate Report
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
{

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timeStamp+".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA SIT");
		extent.setSystemInfo("user","Rajanikant");
		
		htmlReporter.config().setDocumentTitle("iNetBanking Test Project");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	
	}
	
	public void  onTestSuccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName()); // Create New Entry in the Report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		
	}
	
	public void  onTestFailure(ITestResult tr)
	{
		logger = extent.createTest(tr.getName()); // Create New Entry in the Report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		//String sceenshotPath=System.getProperty("user-dir")+"\\Screenshots\\"+tr.getName()+".png";
		String sceenshotPath=(System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png");
		System.out.println("Report SS PATH : "+sceenshotPath);		
		File f = new File(sceenshotPath);
		
		if(f.exists())
		{
		try 
		{
			logger.fail("Screenshot is below"+logger.addScreenCaptureFromPath(sceenshotPath));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}	
		}
		
	}
	
	public void  onTestSkipped(ITestResult tr)
	{
		logger = extent.createTest(tr.getName()); // Create New Entry in the Report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		
	}
	
	public void  onFinish(ITestContext testcontext)
	{
		extent.flush();
		
	}
}
