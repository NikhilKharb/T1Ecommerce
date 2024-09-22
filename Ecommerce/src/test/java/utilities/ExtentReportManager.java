package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter; // UI of report
	public ExtentReports extent; // populate common info
	public ExtentTest test; // creating test case entry and update status of test methods

	String repName;

	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("dd-MM-yyyy-hh-mm").format(new Date());

		repName = "Report-" + timeStamp + ".html";
		// sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify
		// location

		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + repName);

		String s = sparkReporter.toString();
		System.out.println(s);

		sparkReporter.config().setDocumentTitle("Automation test report"); // title of report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
			System.out.println("started end");
		}
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("started success block");
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());// to display groups in reports
		test.log(Status.PASS, result.getName() + " got successfully executed");
		System.out.println("end success block");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		String imgpath = new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgpath);
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		System.out.println("started finish block");
		extent.flush();
		// C:\Users\nikhil.kharb\eclipse-workspace\Ecommerce\reports
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(pathOfExtentReport);
		System.out.println("Report saved");
		try {
			Desktop.getDesktop().browse(extentReport.toURI());

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Report exception");

		}
		/*
		 * try { URL url = new
		 * URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName); //
		 * Create the email message ImageHtmlEmail email = new ImageHtmlEmail();
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setHostName("smtp-googlemail.com"); email.setSmtpPort (465);
		 * email.setAuthenticator(new DefaultAuthenticator("kharb.nikhil10@gmail.com",
		 * "pbht xbxl vgqs hzoi")); email.setSSLOnConnect (true);
		 * email.setFrom("kharb.nikhil10@gmail.com"); //Sender
		 * email.setSubject("Test Results");
		 * email.setMsg("Please find Attached Report.... ");
		 * email.addTo("kharb.nikhil0910@gmail.com"); //Receiver email.attach(url,
		 * "extent report", "please check report..."); email. send(); // send the email
		 * } catch(Exception e) { e.printStackTrace(); }
		 */
	}

}
