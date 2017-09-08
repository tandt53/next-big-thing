package com.thetan.automation.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thetan.automation.example.utils.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class BaseTest<TTest extends BaseTest> {

    public Log TLog = new Log(((TTest) BaseTest.this).getClass());
    public String testName;

    public WebDriver getDriver(String browser) throws IOException {
        Injector injector = Guice.createInjector(new WebDriverInjector());
        WebDriverSelector driverSelector = injector.getInstance(WebDriverSelector.class);
        WebDriver driver = driverSelector.getDriver(browser);
        driver.manage().window().maximize();
        return driver;
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
