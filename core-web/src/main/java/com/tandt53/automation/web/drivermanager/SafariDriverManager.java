package com.tandt53.automation.web.drivermanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;

public class SafariDriverManager extends DriverManager {
    protected static String KEY_SAFARI = "please add safari driver here";

    @Override
    public WebDriver initDriver() {
        driver.set(new SafariDriver());
        return getDriver();
    }

    @Override
    public WebDriver initDriver(DriverService service) {
        return null;
    }

    @Override
    public WebDriver initDriver(Capabilities capabilities) {
        return null;
    }

    @Override
    public WebDriver initDriver(DriverService service, Capabilities caps) {
        return null;
    }

    @Override
    public WebDriver initDriver(URL remoteAddress, Capabilities caps) {
        return null;
    }
}
