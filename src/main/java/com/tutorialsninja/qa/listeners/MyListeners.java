package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;

public class MyListeners implements ITestListener {
	
	ExtentReports extendReport;
	ExtentTest extendTest;
	String testName;

	@Override
	public void onStart(ITestContext context) {
		
		extendReport = ExtentReporter.generateExtendReport();
		//System.out.println("Execution of project test has been started");

	}

	@Override
	public void onTestStart(ITestResult result) {

		testName = result.getName();
		System.out.println(testName + "Started executing");
		extendTest = extendReport.createTest(testName);
		extendTest.log(Status.INFO, testName + "Started Executing");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		testName = result.getName();
		extendTest.log(Status.PASS, testName + "Got successfully Passed");	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		testName = result.getName();
//		result.getTestClass().getRealClass().getDeclaredFields("driver").get
//		File srcScreenshot = ((TakesScreenShot)driver).getScreenshotAs(OutputType.FILE);
		System.out.println("Screenshot Captured");	
		System.out.println(result.getThrowable());
		System.out.println(testName + "Got failed");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		testName = result.getName();
		extendTest.log(Status.INFO, result.getThrowable());
		extendTest.log(Status.SKIP, testName + "Got skipped");
//		System.out.println(testName + "Got skipped");
//		System.out.println(result.getThrowable());

	}

	@Override
	public void onFinish(ITestContext context) {
		extendReport.flush();
//		System.out.println("Finished executing test!!");
		String pathOfExtendReport = System.getProperty("user.dir")+"\\test-output\\extentReports\\extentReport.html";
		File extendReportFile = new File(pathOfExtendReport);
		try {
			Desktop.getDesktop().browse(extendReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
