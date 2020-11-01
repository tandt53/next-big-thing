package com.tandt53.automation.web;

import com.tandt53.automation.web.utils.Log;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.safari.SafariOptions;
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

	public BaseTest() throws IllegalAccessException {
		BrowserFactory.initPages(this);
	}

//	protected WebDriver driver;

	@BeforeSuite
	public void setupSuite() {
		// Initialize components
		// 1. WebDriver
		// 2. Start new session of Appium if needed
//		driver = DriverManagerFactory.getDriver("safari");

		MutableCapabilities caps = new SafariOptions();
		caps.getBrowserName();
//		driver = DriverManagerFactory.getDriver("safari","http://10.124.56.123:4444/wd/hub","remote", caps);
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
//		driver.quit();
	}
}
