package com.tandt53.automation.web.drivermanager;

import com.tandt53.automation.web.drivermanager.options.CapabilityManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {

    protected static String KEY_FIREFOX = "webdriver.gecko.driver";

    @Override
    public WebDriver initDriver() {
        Capabilities caps = CapabilityManager.loadCaps();
        System.setProperty(KEY_FIREFOX, caps.getCapability(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new FirefoxDriver(caps));
        return getDriver();
    }

}
