package com.tandt.automation.example.report;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tandt.automation.example.BasePage;
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
 * @author Carl Cocchiaro
 *
 *         TestNG Listener Utility Class
 *
 */
public class TestNG_ConsoleRunner extends TestListenerAdapter {

	private Log TLog = new Log(this.getClass());
	private static String logFile = null;

	public static Properties CONFIG = null;
	private ExtentReports extent;
	private ExtentTest test;

	/**
	 * onStart method
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

		if (logFile == null) {
			logFile = Constants.LOGFILE_PATH + testContext.getSuite().getName() + "-"
					+ new SimpleDateFormat("MM.dd.yy.HH.mm.ss").format(new Date()) + ".log";
		}

		TLog.info(testContext.getName() + " STARTED");
		extent = new ExtentReports(reportPath, true, DisplayOrder.NEWEST_FIRST);
	}

	/**
	 * onFinish method
	 *
	 * @param testContext
	 */
	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
		TLog.info("Total Passed = " + getPassedTests().size() + ", Total Failed = " + getFailedTests().size()
				+ ", Total Skipped = " + getSkippedTests().size());
		TLog.info(testContext.getName() + " FINISHED");

		extent.flush();
		extent.close();
	}

	/**
	 * onTestStart method
	 *
	 * @param tr
	 */
	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		TLog.startTestCase(tr.getName(), getTestDescription(tr), getTestParams(tr));
	}

	/**
	 * onTestSuccess method
	 *
	 * @param tr
	 */
	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		TLog.endTestCase(tr.getName(), "PASSED");
		buildTestNodes(tr, LogStatus.PASS);
	}

	/**
	 * onTestFailure method
	 *
	 * @param tr
	 */
	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		takeScreenShot(tr.getMethod().getMethodName());
		TLog.endTestCase(tr.getName(), "FAILED", getTestMessage(tr));
		buildTestNodes(tr, LogStatus.FAIL);
	}

	/**
	 * onTestSkipped method
	 *
	 * @param tr
	 */
	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		TLog.endTestCase(tr.getName(), "SKIPPED", getTestMessage(tr));
		takeScreenShot(tr.getMethod().getMethodName());
		buildTestNodes(tr, LogStatus.SKIP);
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
		TLog.configurationError(tr.getInstanceName(), tr.getName(), "FAILED");
	}

	/**
	 * onConfigurationSkip method
	 *
	 * @param tr
	 */
	@Override
	public void onConfigurationSkip(ITestResult tr) {
		super.onConfigurationSkip(tr);
		TLog.configurationError(tr.getInstanceName(), tr.getName(), "SKIPPED");
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
	public String getTestDescription(ITestResult tr) {
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

	/**
	 * writeTestngLog method
	 *
	 * @param logFile
	 * @param line
	 */
	public void writeTestngLog(String logFile, String line) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		File directory = new File(Constants.LOGFILE_PATH);
		File file = new File(logFile);

		try {
			if (!directory.exists()) {
				directory.mkdirs();
			}

			else if (!file.exists()) {
				file.createNewFile();
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));

			if (line.equals(""))
				line = "empty";
			writer.append("[" + dateFormat.format(date) + "] " + line + "\n");
			writer.close();
		}

		catch (IOException e) {
			// do nothing...
		}
	}

	private void buildTestNodes(ITestResult result, LogStatus status) {
		test = extent.startTest(result.getMethod().getMethodName());

		test.setStartedTime(getTime(result.getStartMillis()));
		test.setEndedTime(getTime(result.getEndMillis()));

		long executionTime = result.getEndMillis() - result.getStartMillis();

		test.log(status, "Execution time: " + executionTime + " ms");
		test.log(status, "Class: " + result.getTestClass().toString());

		for (String group : result.getMethod().getGroups())
			test.assignCategory(group);

		if (result.getThrowable() != null) {
			test.log(status, test.addScreenCapture(result.getMethod().getMethodName() + ".png"));
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

}
