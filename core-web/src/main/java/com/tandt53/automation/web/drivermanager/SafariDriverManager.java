package com.tandt53.automation.web.drivermanager;

import com.tandt53.automation.web.drivermanager.options.CapabilityManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SafariDriverManager extends DriverManager {
    protected static String KEY_SAFARI = "please add safari driver here";

    @Override
    public WebDriver initDriver() {
        Capabilities caps = CapabilityManager.loadCaps();
        System.setProperty(KEY_SAFARI, caps.getCapability(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new SafariDriver(caps));
        return getDriver();
    }

}
