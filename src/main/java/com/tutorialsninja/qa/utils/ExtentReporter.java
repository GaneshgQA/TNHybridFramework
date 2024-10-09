package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtendReport() {

		ExtentReports extendReport = new ExtentReports();
		File extentReportFile = new File(
				System.getProperty("user.dir") + "\\test-output\\extentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Project Automation Test result");
		sparkReporter.config().setDocumentTitle("Ganesh Reports");
		sparkReporter.config().setTimeStampFormat("DD/MM/YYYY HH:MM:SS");
		extendReport.attachReporter(sparkReporter);

		Properties configProp = new Properties();
		File configPropFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(configPropFile);
			configProp.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		extendReport.setSystemInfo("App URL", configProp.getProperty("url"));
		extendReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extendReport.setSystemInfo("User Id", configProp.getProperty("validEmail"));
		extendReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		extendReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extendReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extendReport.setSystemInfo("Java Version : ", System.getProperty("java.version"));

		return extendReport;
	}

}
