package com.thetan.automation.example;

import com.google.inject.Provider;
import com.thetan.automation.example.driver.AbstractWebDriver;
import com.thetan.automation.example.driver.LinuxWebDriver;
import com.thetan.automation.example.driver.MacWebDriver;
import com.thetan.automation.example.driver.WindowsWebDriver;

/**
 * Created by thetan.do on 12/24/2016.
 */
public class WebDriverSelectorProvider implements Provider<AbstractWebDriver> {
    private static String os = System.getProperty("os.name").toLowerCase();

    public AbstractWebDriver get() {

        AbstractWebDriver abstractWebDriver = null;

        if (os.indexOf("win") >= 0) {
            abstractWebDriver = new WindowsWebDriver();
        } else if (os.indexOf("lin") >= 0) {
            abstractWebDriver = new LinuxWebDriver();
        } else if (os.indexOf("mac") >= 0) {
            abstractWebDriver = new MacWebDriver();
        }
        return abstractWebDriver;
    }

}
