package com.thetan.automation.example.driver;

import org.openqa.selenium.WebDriver;

/**
 * Created by thetan.do on 12/24/2016.
 */
public class MacWebDriver extends AbstractWebDriver {
    WebDriver driver = null;

    public WebDriver getDriver(String browser) {
        return driver;
    }

    protected void firefoxDriver() {
    }

    protected void chromeDriver() {
    }

    protected void edgeDriver() {
    }

    protected void safariDriver() {
    }
}
