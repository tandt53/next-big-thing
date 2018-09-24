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

		extent = new ExtentReports(reportPath, true, DisplayOrder.NEWEST_FIRST);

		log.info("START TEST: " + testContext.getName());

	}

	/**
	 * onFinish method
	 *
	 * @param testContext
	 */
	@Override
	public void onFinish(ITestContext testContext) {

		super.onFinish(testContext);
		extent.flush();
		extent.close();
		
		log.info("Total Passed = " + getPassedTests().size());
		log.info("Total Failed = " + getFailedTests().size());
		log.info("Total Skipped = " + getSkippedTests().size());
		log.info("END TEST: " + testContext.getName());
	}

	/**
	 * onTestStart method
	 *
	 * @param tr
	 */
	@Override
	public void onTestStart(ITestResult tr) {
		log.info("START-> " + tr.getName());
		log.info("Test Parameters = " + getTestParams(tr));

		super.onTestStart(tr);
	}

	/**
	 * onTestSuccess method
	 *
	 * @param tr
	 */
	@Override
	public void onTestSuccess(ITestResult tr) {
		log.info("Result = PASSED");
		log.info("END  -> " + tr.getName());
		super.onTestSuccess(tr);
		buildTestNodes(tr, LogStatus.PASS);
	}

	/**
	 * onTestFailure method
	 *
	 * @param tr
	 */
	@Override
	public void onTestFailure(ITestResult tr) {
		if (!getTestMessage(tr).equals("")) {
			log.info(getTestMessage(tr));
		}

		log.info("Result = FAILED");
		log.info("END  -> " + tr.getInstanceName() + "." + tr.getName());

		takeScreenShot(tr.getMethod().getMethodName());
		super.onTestFailure(tr);
		buildTestNodes(tr, LogStatus.FAIL);
	}

	/**
	 * onTestSkipped method
	 *
	 * @param tr
	 */
	@Override
	public void onTestSkipped(ITestResult tr) {
		if (!getTestMessage(tr).equals("")) {
			log.info(getTestMessage(tr));
		}

		log.info("Result = SKIPPED");
		log.info("END  -> " + tr.getInstanceName() + "." + tr.getName());

		takeScreenShot(tr.getMethod().getMethodName());
		super.onTestSkipped(tr);
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
		if (!getTestMessage(tr).equals("")) {
			log.info(getTestMessage(tr));
		}

		log.info("Result = CONFIGURATION FAILED");
		log.info("END CONFIG -> " + tr.getInstanceName() + "." + tr.getName());

		super.onConfigurationFailure(tr);
	}

	/**
	 * onConfigurationSkip method
	 *
	 * @param tr
	 */
	@Override
	public void onConfigurationSkip(ITestResult tr) {
		log.info(getTestMessage(tr));
		log.info("Result = CONFIGURATION SKIPPED");
		log.info("END CONFIG -> " + tr.getInstanceName() + "." + tr.getName());

		super.onConfigurationSkip(tr);
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

			if (line.contains("START") || line.contains("END")) {
				writer.append("[" + dateFormat.format(date) + "] " + line);

			}

			else {
				writer.append(line);
			}

			writer.newLine();
			writer.close();
		}

		catch (IOException e) {
			// do nothing...
		}
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

}
