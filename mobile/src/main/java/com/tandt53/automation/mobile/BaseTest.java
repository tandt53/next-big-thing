package com.tandt53.automation.mobile;

import com.tandt53.automation.common.Log;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

/**
 * Created by thetan.do on 12/28/2016.
 */

public class BaseTest<TTest extends BaseTest> {

	@SuppressWarnings("unchecked")
	public Log TLog = new Log(((TTest) BaseTest.this).getClass());
	private String testName;

	public BaseTest() throws IllegalAccessException {
		BrowserFactory.initPages(this);
	}


	@BeforeSuite
	public void setupSuite() {
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
