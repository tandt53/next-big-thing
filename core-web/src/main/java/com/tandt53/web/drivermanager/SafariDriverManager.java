package com.tandt53.web.drivermanager;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SafariDriverManager extends DriverManager {
    protected static String KEY_SAFARI = "please add safari driver here";

    @Override
    public WebDriver initDriver() {
        Capability caps = CapabilityManager.loadCaps();
        System.setProperty(KEY_SAFARI, caps.getValue(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new SafariDriver(new MutableCapabilities(caps.getMap())));
        return getDriver();
    }

}