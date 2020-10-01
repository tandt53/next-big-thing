package com.tandt.automation.web;

import com.tandt.automation.web.drivermanager.DriverManagerFactory;
import com.tandt.automation.web.utils.Log;

import org.openqa.selenium.WebDriver;
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

	protected WebDriver driver;

	@BeforeSuite
	public void setupSuite() {
		// Initialize components
		// 1. WebDriver
		// 2. Start new session of Appium if needed
		driver = DriverManagerFactory.getDriver("chrome");
	}

	@BeforeMethod
	public void setupMethod(Method method) {
		testName = method.getName();
	}

	@AfterMethod
	public void teardownMethod(Method method) {
	}
	
	@AfterSuite
	public void teardownSuite() {
	}
}
