package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBases.BaseClass;
//import testBases.SeleniumGridBaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	// Method to execute at the start of very first Test
	public void onStart(ITestContext testContext) {
		// Report Name capturing
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		spark = new ExtentSparkReporter(".\\reports\\" + repName);

		// Setting report Theme and Title
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("UAT Testing");
		spark.config().setTheme(Theme.DARK);

		// Capturing the details that need to be shown in your Reports
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Application", "OpenCart Ecommerce Application");
		// extent.setSystemInfo("Module", "Admin");
		// extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		// will get the Operating system parameter from the current XML file used for
		// running the test
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		// will get the browser parameter from the current XML file used for running the
		// test
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		// If there is any Groups the below will capture the groups from the current XML
		// file used for running the test
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}

	}

	// Method to execute if a test got Passed
	public void onTestSuccess(ITestResult result) {
		//test = extent.createTest(result.getTestClass().getName()); // get test name in the extent report
		test= extent.createTest(result.getMethod().getMethodName());// get method name in the extent report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got successfully executed");

	}

	// Method to execute if a test got Failed
	public void onTestFailure(ITestResult result) {
		test= extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		// Capture the screenshot Path and attach it to the report if Test Failed
		// method to capture screen shot path is already stored in base class
		try {
			
			String imgPath = new BaseClass().CaptureScreenShotPath(result.getName());
			//String imgPath = new SeleniumGridBaseClass().CaptureScreenShotPath(result.getName()); use this when running in grid
			test.addScreenCaptureFromPath(imgPath);// attach the screenshot using its path to the report
		} catch (IOException e) {
			e.printStackTrace();// catch the FielNOTFoundException if thrown
		}
	}

	// Method to execute if a test got Skipped
	public void onTestSkipped(ITestResult result) {
		test= extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	// Method to execute if a test got Skipped
	public void onFinish(ITestContext testContext) {

		extent.flush(); // will generate the report

		// below is automatically Open the report on the browser automatically once the
		// Test is finished
		String ERpath = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(ERpath);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();// to catch the exception if the report not generated or found
		}
	}

}
