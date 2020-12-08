package com.tandt53.automation.web;

/**
 * Created by thetan.do on 12/28/2016.
 */

public class BaseTest<TTest extends BaseTest<?>> {

	@SuppressWarnings("unchecked")
//	public Log TLog = new Log(((TTest) BaseTest.this).getClass());
//	private String testName;

	public BaseTest() throws IllegalAccessException {
		BrowserFactory.initPages(this);
	}


//	@BeforeSuite
//	public void setupSuite() {
//	}
//
//	@BeforeMethod
//	public void setupMethod(Method method) {
//		testName = method.getName();
//	}
//
//	@AfterMethod
//	public void teardownMethod(Method method) {
//	}
//
//	@AfterSuite
//	public void teardownSuite() {
//	}
}
