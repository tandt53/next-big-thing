package com.tandt53.automation.web.drivermanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.net.URL;

public abstract class DriverManager {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver(){
        return driver.get();
    }

    public abstract WebDriver initDriver();

    public abstract WebDriver initDriver(DriverService service);

    public abstract WebDriver initDriver(Capabilities capabilities);

    public abstract WebDriver initDriver(DriverService service, Capabilities caps);

    public abstract WebDriver initDriver(String browser, URL remoteAddress, Capabilities caps);

    protected String currentOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return Constants.OS_WIN;
        } else if (os.contains("lin")) {
            return Constants.OS_LIN;
        } else if (os.contains("mac")) {
            return Constants.OS_MAC;
        } else
            return Constants.OS_UNDEFINED;
    }

}
