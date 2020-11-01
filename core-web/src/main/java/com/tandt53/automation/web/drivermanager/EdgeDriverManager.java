package com.tandt53.automation.web.drivermanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.net.URL;

public class EdgeDriverManager extends DriverManager{

    protected static String KEY_EDGE = "webdriver.edge.driver";

    @Override
    public WebDriver initDriver() {
        return null;
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
    public WebDriver initDriver(String browser, URL remoteAddress, Capabilities caps) {
        return null;
    }
}
