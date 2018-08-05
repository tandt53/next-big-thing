package com.tandt.automation.example;

import com.tandt.automation.example.driver.DriverManager;
import com.tandt.automation.example.utils.Log;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class BaseTest<TTest extends BaseTest<?>> {

	@SuppressWarnings("unchecked")
	public Log TLog = new Log(((TTest) BaseTest.this).getClass());
	private String testName;

	@BeforeSuite
	public void setupSuite() {
		// Initialize components
		// 1. WebDriver
		// 2. Start new session of Appium if needed
		DriverManager.initDriver();
	}

	@BeforeMethod
	public void setupMethod(Method method) {
		testName = method.getName();
		TLog.startTestCase(testName);
	}

	@AfterMethod
	public void teardownMethod(Method method) {
		TLog.endTestCase(testName);
	}
	
	@AfterSuite
	public void teardownSuite() {
	}
}
