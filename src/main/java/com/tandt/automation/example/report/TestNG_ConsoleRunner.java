package com.tandt.automation.example.report;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tandt.automation.example.driver.DriverManager;
import com.tandt.automation.example.utils.Constants;
import com.tandt.automation.example.utils.Log;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * @author tandt
 *
 *         TestNG Listener Utility Class
 *
 */
public class TestNG_ConsoleRunner extends TestListenerAdapter {

	public static Properties CONFIG = null;
	private ExtentReports extent;
	private ExtentTest test;

	private static Log log = new Log(TestNG_ConsoleRunner.class);

	/**
	 * onStart method is execute when TEST (refer <test> tag in testng.xml) is
	 * started
	 *
	 * @param testContext
	 */
	@Override
	public void onStart(ITestContext testContext) {
		super.onStart(testContext);

		String reportPath = Constants.REPORT_PATH + "report.html";
		File report = new File(reportPath);
		if (report.exists())
			report.delete();
		extent = new ExtentReports(reportPath, true, DisplayOrder.NEWEST_FIRST);

		onStart(testContext.getName());
	}

	/**
	 * onFinish method is execute when TEST (refer <test> tag in testng.xml) is
	 * finised
	 *
	 * @param testContext
	 */
	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
		extent.flush();
		extent.close();
		onFinish(testContext.getName(), getPassedTests().size(), getFailedTests().size(), getSkippedTests().size());
	}

	/**
	 * onTestStart method
	 *
	 * @param tr
	 */
	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		startTestCase(tr.getName(), getTestDescription(tr), getTestParams(tr));
	}

	/**
	 * onTestSuccess method
	 *
	 * @param tr
	 */
	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		buildTestNodes(tr, LogStatus.PASS);
		endTestCase(getTestName(tr), "PASSED");
	}

	/**
	 * onTestFailure method
	 *
	 * @param tr
	 */
	@Override
	public void onTestFailure(ITestResult tr) {
		takeScreenShot(tr.getMethod().getMethodName());
		super.onTestFailure(tr);
		buildTestNodes(tr, LogStatus.FAIL);
		endTestCase(getTestName(tr), "FAILED", getTestMessage(tr), getStackTrace(tr));
	}

	/**
	 * onTestSkipped method
	 *
	 * @param tr
	 */
	@Override
	public void onTestSkipped(ITestResult tr) {
		takeScreenShot(tr.getMethod().getMethodName());
		super.onTestSkipped(tr);
		buildTestNodes(tr, LogStatus.SKIP);
		endTestCase(getTestName(tr), "SKIPPED", getTestMessage(tr), getStackTrace(tr));
	}

	/**
	 * takeScreenShot method
	 * 
	 * @param testName
	 * 
	 */
	private void takeScreenShot(String testName) {
		DriverManager.takeScreenShot(testName);
	}

	/**
	 * onConfigurationSuccess method
	 *
	 * @param itr
	 */
	@Override
	public void onConfigurationSuccess(ITestResult itr) {
		super.onConfigurationSuccess(itr);
	}

	/**
	 * onConfigurationFailure method
	 *
	 * @param tr
	 */
	@Override
	public void onConfigurationFailure(ITestResult tr) {
		super.onConfigurationFailure(tr);
		endTestCase(getTestName(tr), "CONFIGURATION FAILED", getTestMessage(tr), getStackTrace(tr));
	}

	/**
	 * onConfigurationSkip method
	 *
	 * @param tr
	 */
	@Override
	public void onConfigurationSkip(ITestResult tr) {

		super.onConfigurationSkip(tr);
		endTestCase(getTestName(tr), "CONFIGURATION SKIPPED", getTestMessage(tr), getStackTrace(tr));
	}

	private String[] getStackTrace(ITestResult tr) {
		StackTraceElement[] stackTraces = tr.getThrowable().getStackTrace();
		String[] stackTraceLogs = new String[stackTraces.length];
		for (int index = 0; index < stackTraces.length; index++) {
			stackTraceLogs[index] = stackTraces[index].toString();
		}
		return stackTraceLogs;
	}

	/**
	 * getTestName method
	 * 
	 * @param tr
	 * @return String
	 */
	public String getTestName(ITestResult tr) {
		return tr.getName();
	}

	/**
	 * getTestMessage method
	 *
	 * @param tr
	 * @return String
	 */
	public String getTestMessage(ITestResult tr) {
		Boolean found = false;

		if (tr != null && tr.getThrowable() != null) {
			found = true;
		}

		if (found == true) {
			return tr.getThrowable().getMessage() == null ? "" : tr.getThrowable().getMessage();
		}

		else {
			return "";
		}
	}

	/**
	 * getTestParams method
	 *
	 * @param tr
	 * @return String
	 */
	public String getTestParams(ITestResult tr) {
		int iLength = tr.getParameters().length;
		String message = "";

		try {
			if (tr.getParameters().length > 0) {
				message = tr.getParameters()[0].toString();

				for (int iCount = 0; iCount < iLength; iCount++) {
					if (iCount == 0) {
						message = tr.getParameters()[0].toString();
					} else {
						message = message + ", " + tr.getParameters()[iCount].toString();
					}
				}
			}
		}

		catch (Exception e) {
			// do nothing...
		}

		return message;
	}

	/**
	 * getTestDescription method
	 *
	 * @param tr
	 * @return String
	 */
	private String getTestDescription(ITestResult tr) {
		String message = "";

		try {
			if (tr.getParameters().length > 0) {
				message = ": " + tr.getParameters()[1].toString();
			}
		}

		catch (Exception e) {
			// do nothing...
		}

		return message;
	}

	private void buildTestNodes(ITestResult result, LogStatus status) {
		// ExtentTest test;

		test = extent.startTest(result.getMethod().getMethodName());

		test.setStartedTime(getTime(result.getStartMillis()));
		test.setEndedTime(getTime(result.getEndMillis()));

		long executionTime = result.getEndMillis() - result.getStartMillis();

		test.log(status, "Execution time:  " + executionTime + " ms");
		test.log(status, "Class: " + result.getTestClass().toString());

		for (String group : result.getMethod().getGroups())
			test.assignCategory(group);

		if (result.getThrowable() != null) {
			test.log(status,
					test.addScreenCapture(/* Constants.REPORT_PATH + */result.getMethod().getMethodName() + ".png"));
			System.out.println("Screenshot is " + Constants.REPORT_PATH + result.getMethod().getMethodName() + ".png");
			test.log(status, result.getThrowable());

		} else {
			test.log(status, "Test " + status.toString().toLowerCase() + "ed");
		}

		extent.endTest(test);

		extent.flush();
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	private void startTestCase(String testName, String description, String params) {
		log.info("------------------------- Started Test '" + testName + "' -------------------------");
		log.info("Decription: " + description);
		log.info("Parameters: " + params);
	}

	private void endTestCase(String testName, String result) {
		log.info("Result: " + result);
		log.info("------------------------- Ended Test '" + testName + "' -------------------------");
	}

	private void endTestCase(String testName, String result, String testMessage, String[] stackTrace) {
		log.info("Result: " + result);
		log.info("Message: " + testMessage);
		log.error(stackTrace);
		log.info("------------------------- Ended Test '" + testName + "' -------------------------");
	}

	private void onStart(String test) {
		log.info("***** START TEST: " + test);
	}

	private void onFinish(String name, int pass, int fail, int skip) {
		log.info("***** END TEST: " + name + " (" + "PASSED: " + pass + "; " + "FAILED: " + fail + "; " + "SKIPPED: "
				+ skip + ")");
	}

}
