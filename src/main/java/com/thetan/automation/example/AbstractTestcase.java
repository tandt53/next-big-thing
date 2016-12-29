package com.thetan.automation.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thetan.automation.example.utils.Log;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class AbstractTestcase<TTest extends AbstractTestcase> {

    public Log TLog = new Log(((TTest)AbstractTestcase.this).getClass());
    public String testName;

    public WebDriver getDriver(String browser) {
        Injector injector = Guice.createInjector(new WebDriverInjector());
        WebDriverSelector driverSelector = injector.getInstance(WebDriverSelector.class);
        WebDriver driver = driverSelector.getDriver(browser);
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod
    public void setupMethod(Method method){
        testName = method.getName();
        TLog.startTestCase(testName);
    }

    @AfterMethod
    public void teardownMethod(Method method){
        TLog.endTestCase(testName);
    }
}
