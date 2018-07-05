package com.thetan.automation.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thetan.automation.example.driver.provider.WebDriverInjector;
import com.thetan.automation.example.driver.provider.WebDriverSelector;
import com.thetan.automation.example.utils.LoadConfig;
import com.thetan.automation.example.utils.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class BaseTest<TTest extends BaseTest> {

	public Log TLog = new Log(((TTest) BaseTest.this).getClass());
	public String testName;


	@BeforeSuite
	public void setupSuite() {
		
	}

	private void initDriver() {
		// TODO Auto-generated method stub

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
}
