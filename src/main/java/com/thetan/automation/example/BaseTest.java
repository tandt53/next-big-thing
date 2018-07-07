package com.thetan.automation.example;

import com.google.common.base.Strings;
import com.thetan.automation.example.utils.Constants;
import com.thetan.automation.example.utils.LoadConfig;
import com.thetan.automation.example.utils.Log;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
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
