//package com.tandt53.automation.web.report;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.Protocol;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.tandt53.automation.common.Log;
//import com.tandt53.automation.web.utils.Constants;
//import org.testng.ITestContext;
//import org.testng.ITestResult;
//import org.testng.TestListenerAdapter;
//
//import java.io.*;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Properties;
//
///**
// * @author tandt
// *
// *         TestNG Listener Utility Class
// *
// */
//public class TestReportListener extends TestListenerAdapter {
//	private String bitmapDir = Constants.REPORT_PATH;
//	public static Properties CONFIG = null;
//	private ExtentReports extentReport;
//	private ExtentTest extentTest;
//
//	private static Log log = new Log(TestReportListener.class);
//
//	/**
//	 * onStart method is execute when TEST (refer <test> tag in testng.xml) is
//	 * started
//	 *
//	 * @param testContext
//	 */
//	@Override
//	public void onStart(ITestContext testContext) {
//		super.onStart(testContext);
//
//		initHTMLReport(testContext.getSuite().getName());
//
//		onStart(testContext.getName());
//	}
//
//	private void initHTMLReport(String suiteName) {
//
//		File directory = new File(Constants.REPORT_PATH);
//
//		if (!directory.exists()) {
//			directory.mkdirs();
//		}
//
//		String htmlReportPath = Constants.REPORT_PATH + "report.html";
//		File htmlReport = new File(htmlReportPath);
//		if (htmlReport.exists())
//			htmlReport.delete();
//
//		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(htmlReportPath);
//
//		// report attributes
//		htmlReporter.config().setDocumentTitle(suiteName.replace("_", " "));
//		htmlReporter.config().setReportName(suiteName.replace("_", " "));
//		htmlReporter.config().setChartVisibilityOnOpen(false);
//		htmlReporter.config().setTheme(Theme.STANDARD);
//		htmlReporter.config().setEncoding("UTF-8");
//		htmlReporter.config().setProtocol(Protocol.HTTPS);
//		htmlReporter.config().setTimeStampFormat("MMM-dd-yyyy HH:mm:ss a");
//		htmlReporter.config().setChartVisibilityOnOpen(true);
////		htmlReporter.loadXMLConfig(new File(Constants.REPORT_CONFIG_FILE));
//
//		extentReport = new ExtentReports();
//
//		// report system info
//		extentReport.setSystemInfo("Browser", Constants.DEF_BROWSER);
//		extentReport.setSystemInfo("Environment", Constants.DEF_ENVIRONMENT);
//		extentReport.setSystemInfo("Platform", Constants.DEF_PLATFORM);
//		extentReport.setSystemInfo("OS Version", System.getProperty("os.name"));
//		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
//		// extent.setSystemInfo("Selenium Version", seleniumRev);
//
//		extentReport.attachReporter(htmlReporter);
//		extentReport.setReportUsesManualConfiguration(true);
//
//		extentReport.flush();
//
//	}
//
//	/**
//	 * onFinish method is execute when TEST (refer <test> tag in testng.xml) is
//	 * finised
//	 *
//	 * @param testContext
//	 */
//	@Override
//	public void onFinish(ITestContext testContext) {
//		super.onFinish(testContext);
//		extentReport.flush();
//		onFinish(testContext.getName(), getPassedTests().size(), getFailedTests().size(), getSkippedTests().size());
//	}
//
//	/**
//	 * onTestStart method
//	 *
//	 * @param tr
//	 */
//	@Override
//	public void onTestStart(ITestResult tr) {
//		super.onTestStart(tr);
//		startTestCase(tr.getName(), getTestDescription(tr), getTestParams(tr));
//	}
//
//	/**
//	 * onTestSuccess method
//	 *
//	 * @param tr
//	 */
//	@Override
//	public void onTestSuccess(ITestResult tr) {
//		super.onTestSuccess(tr);
//		buildTestNodes(tr, Status.PASS);
//		endTestCase(getTestName(tr), "PASSED");
//	}
//
//	/**
//	 * onTestFailure method
//	 *
//	 * @param tr
//	 */
//	@Override
//	public void onTestFailure(ITestResult tr) {
//		takeScreenShot(tr.getMethod().getMethodName());
//		super.onTestFailure(tr);
//		buildTestNodes(tr, Status.FAIL);
//		endTestCase(getTestName(tr), "FAILED", getTestMessage(tr), getStackTrace(tr));
//	}
//
//	/**
//	 * onTestSkipped method
//	 *
//	 * @param tr
//	 */
//	@Override
//	public void onTestSkipped(ITestResult tr) {
//		takeScreenShot(tr.getMethod().getMethodName());
//		super.onTestSkipped(tr);
//		buildTestNodes(tr, Status.SKIP);
//		endTestCase(getTestName(tr), "SKIPPED", getTestMessage(tr), getStackTrace(tr));
//	}
//
//	/**
//	 * takeScreenShot method
//	 *
//	 * @param testName
//	 *
//	 */
//	private void takeScreenShot(String testName) {
////		DriverManager.takeScreenShot(testName);
//	}
//
//	/**
//	 * onConfigurationSuccess method
//	 *
//	 * @param itr
//	 */
//	@Override
//	public void onConfigurationSuccess(ITestResult itr) {
//		super.onConfigurationSuccess(itr);
//	}
//
//	/**
//	 * onConfigurationFailure method
//	 *
//	 * @param tr
//	 */
//	@Override
//	public void onConfigurationFailure(ITestResult tr) {
//		super.onConfigurationFailure(tr);
//		endTestCase(getTestName(tr), "CONFIGURATION FAILED", getTestMessage(tr), getStackTrace(tr));
//	}
//
//	/**
//	 * onConfigurationSkip method
//	 *
//	 * @param tr
//	 */
//	@Override
//	public void onConfigurationSkip(ITestResult tr) {
//
//		super.onConfigurationSkip(tr);
//		endTestCase(getTestName(tr), "CONFIGURATION SKIPPED", getTestMessage(tr), getStackTrace(tr));
//	}
//
//	private String[] getStackTrace(ITestResult tr) {
//		StackTraceElement[] stackTraces = tr.getThrowable().getStackTrace();
//		String[] stackTraceLogs = new String[stackTraces.length];
//		for (int index = 0; index < stackTraces.length; index++) {
//			stackTraceLogs[index] = stackTraces[index].toString();
//		}
//		return stackTraceLogs;
//	}
//
//	/**
//	 * getTestName method
//	 *
//	 * @param tr
//	 * @return String
//	 */
//	public String getTestName(ITestResult tr) {
//		return tr.getName();
//	}
//
//	/**
//	 * getTestMessage method
//	 *
//	 * @param tr
//	 * @return String
//	 */
//	public String getTestMessage(ITestResult tr) {
//		Boolean found = false;
//
//		if (tr != null && tr.getThrowable() != null) {
//			found = true;
//		}
//
//		if (found == true) {
//			return tr.getThrowable().getMessage() == null ? "" : tr.getThrowable().getMessage();
//		}
//
//		else {
//			return "";
//		}
//	}
//
//	/**
//	 * getTestParams method
//	 *
//	 * @param tr
//	 * @return String
//	 */
//	public String getTestParams(ITestResult tr) {
//		int iLength = tr.getParameters().length;
//		String message = "";
//
//		try {
//			if (tr.getParameters().length > 0) {
//				message = tr.getParameters()[0].toString();
//
//				for (int iCount = 0; iCount < iLength; iCount++) {
//					if (iCount == 0) {
//						message = tr.getParameters()[0].toString();
//					} else {
//						message = message + ", " + tr.getParameters()[iCount].toString();
//					}
//				}
//			}
//		}
//
//		catch (Exception e) {
//			// do nothing...
//		}
//
//		return message;
//	}
//
//	/**
//	 * getTestDescription method
//	 *
//	 * @param tr
//	 * @return String
//	 */
//	private String getTestDescription(ITestResult tr) {
//		String message = "";
//
//		try {
//			if (tr.getParameters().length > 0) {
//				message = ": " + tr.getParameters()[1].toString();
//			}
//		}
//
//		catch (Exception e) {
//			// do nothing...
//		}
//
//		return message;
//	}
//
//	private void buildTestNodes(ITestResult result, Status status) {
//
//		String message = null;
//
//		if (getTestParams(result).isEmpty()) {
//			extentTest = extentReport.createTest(result.getMethod().getMethodName());
//		}else {
//			if (getTestParams(result).split(",")[0].contains(result.getMethod().getMethodName())) {
//				extentTest = extentReport.createTest(getTestParams(result).split(",")[0], getTestParams(result).split(",")[1]);
//			}
//
//			else {
//				extentTest = extentReport.createTest(result.getMethod().getMethodName(), getTestParams(result).split(",")[1]);
//			}
//		}
//
//		extentTest.getModel().setStartTime(getTime(result.getStartMillis()));
//		extentTest.getModel().setEndTime(getTime(result.getEndMillis()));
//
//		for (String group : result.getMethod().getGroups()) {
//			if (!group.isEmpty()) {
//				extentTest.assignCategory(group);
//			}
//
//			else {
//				int size = result.getMethod().getTestClass().toString().split("\\.").length;
//				String testName = result.getMethod().getRealClass().getName().toString().split("\\.")[size - 1];
//				extentTest.assignCategory(testName);
//			}
//		}
//
//		// get status
//		switch (result.getStatus()) {
//		case 1:
//			status = Status.PASS;
//			break;
//		case 2:
//			status = Status.FAIL;
//			break;
//		case 3:
//			status = Status.SKIP;
//			break;
//		:
//			status = Status.INFO;
//			break;
//		}
//
//		// set colors of status
//		if (status.equals(Status.PASS)) {
//			message = "<font color=#00af00>" + status.toString().toUpperCase() + "</font>";
//		}
//
//		else if (status.equals(Status.FAIL)) {
//			message = "<font color=#F7464A>" + status.toString().toUpperCase() + "</font>";
//		}
//
//		else if (status.equals(Status.SKIP)) {
//			message = "<font color=#2196F3>" + status.toString().toUpperCase() + "</font>";
//		}
//
//		else {
//			message = "<font color=black>" + status.toString().toUpperCase() + "</font>";
//		}
//
//		// log status in report
//		extentTest.log(status, message);
//
//		if (!getTestParams(result).isEmpty()) {
//			extentTest.log(Status.INFO, "TEST DATA = [" + getTestParams(result) + "]");
//		}
//
//		if (result.getThrowable() != null) {
//			extentTest.log(status, "" + result.getThrowable().getMessage());
//			try {
//				extentTest.log(status, "SCREENSHOT",
//						MediaEntityBuilder
//								.createScreenCaptureFromPath(
//										System.getProperty("user.dir") + "/" + bitmapDir + result.getName() + ".png")
//								.build());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			if (!getTestParams(result).isEmpty()) {
//				if (result.getAttribute("testBitmap") != null) {
//					try {
//						extentTest.log(status, "SCREENSHOT", MediaEntityBuilder
//								.createScreenCaptureFromPath(bitmapDir + result.getAttribute("testBitmap")).build());
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//
//			}
//			extentTest.log(status, "STACKTRACE" + getStrackTrace(result));
//		}
//
//		extentReport.flush();
//
//	}
//
//	private Date getTime(long millis) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(millis);
//		return calendar.getTime();
//	}
//
//	private void startTestCase(String testName, String description, String params) {
//		log.info("------------------------- Started Test '" + testName + "' -------------------------");
//		log.info("Decription: " + description);
//		log.info("Parameters: " + params);
//	}
//
//	private void endTestCase(String testName, String result) {
//		log.info("Result: " + result);
//		log.info("------------------------- Ended Test '" + testName + "' -------------------------");
//	}
//
//	private void endTestCase(String testName, String result, String testMessage, String[] stackTrace) {
//		log.info("Result: " + result);
//		log.info("Message: " + testMessage);
//		log.error(stackTrace);
//		log.info("------------------------- Ended Test '" + testName + "' -------------------------");
//	}
//
//	private void onStart(String test) {
//		log.info("***** START TEST: " + test);
//	}
//
//	private void onFinish(String name, int pass, int fail, int skip) {
//		log.info("***** END TEST: " + name + " (" + "PASSED: " + pass + "; " + "FAILED: " + fail + "; " + "SKIPPED: "
//				+ skip + ")");
//	}
//
//	/**
//	 * getStrackTrace method to retrieve stack trace
//	 *
//	 * @param result
//	 * @return String
//	 */
//	private String getStrackTrace(ITestResult result) {
//		Writer writer = new StringWriter();
//		PrintWriter printWriter = new PrintWriter(writer);
//		result.getThrowable().printStackTrace(printWriter);
//
//		return "<br/>\n" + writer.toString().replace(System.lineSeparator(), "<br/>\n");
//	}
//}
