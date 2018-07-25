package com.tandt.automation.example.driver.provider;

import com.google.inject.Provider;
import com.tandt.automation.example.driver.BaseWebDriver;
import com.tandt.automation.example.driver.LinuxWebDriver;
import com.tandt.automation.example.driver.MacWebDriver;
import com.tandt.automation.example.driver.WindowsWebDriver;

/**
 * Created by thetan.do on 12/24/2016.
 */
public class WebDriverSelectorProvider implements Provider<BaseWebDriver> {
    private static String os = System.getProperty("os.name").toLowerCase();

    @Override
    public BaseWebDriver get() {

        BaseWebDriver abstractWebDriver = null;

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
