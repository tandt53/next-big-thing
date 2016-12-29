package com.thetan.automation.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thetan.automation.example.utils.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class AbstractTestcase<TTest extends AbstractTestcase> {

    public Log TLog = new Log(((TTest)AbstractTestcase.this).getClass());

    public WebDriver getDriver(String browser) {
        Injector injector = Guice.createInjector(new WebDriverInjector());
        WebDriverSelector driverSelector = injector.getInstance(WebDriverSelector.class);
        WebDriver driver = driverSelector.getDriver(browser);
        driver.manage().window().maximize();
        return driver;
    }


}
