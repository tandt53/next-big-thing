package com.tandt.automation.web.drivermanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.net.URL;

public class SafariDriverManager extends DriverManager {
    protected static String KEY_SAFARI = "please add safari driver here";

    @Override
    public WebDriver getDriver() {
        return null;
    }

    @Override
    public WebDriver getDriver(DriverService service) {
        return null;
    }

    @Override
    public WebDriver getDriver(Capabilities capabilities) {
        return null;
    }

    @Override
    public WebDriver getDriver(DriverService service, Capabilities caps) {
        return null;
    }

    @Override
    public WebDriver getDriver(String browser, URL remoteAddress, Capabilities caps) {
        return null;
    }
}
