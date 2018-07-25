package com.tandt.automation.example.driver.provider;

import com.google.inject.Inject;
import com.tandt.automation.example.driver.BaseWebDriver;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by thetan.do on 12/24/2016.
 */

public class WebDriverSelector {
    BaseWebDriver abstractWebDriver;

    @Inject
    public WebDriverSelector(BaseWebDriver abstractWebDriver){
        this.abstractWebDriver = abstractWebDriver;
    }

    public WebDriver getDriver(String browser) throws IOException {
         return abstractWebDriver.getDriver(browser);
    }



}
