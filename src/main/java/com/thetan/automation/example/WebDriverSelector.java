package com.thetan.automation.example;

import com.google.inject.Inject;
import com.thetan.automation.example.driver.AbstractWebDriver;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by thetan.do on 12/24/2016.
 */

public class WebDriverSelector {
    AbstractWebDriver abstractWebDriver;

    @Inject
    public WebDriverSelector(AbstractWebDriver abstractWebDriver/*, String browser*/){
        this.abstractWebDriver = abstractWebDriver;
    }

    public WebDriver getDriver(String browser) throws IOException {
         return abstractWebDriver.getDriver(browser);
    }



}
