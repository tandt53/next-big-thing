package com.tandt.automation.example.refactor.drivermanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.net.URL;

public abstract class DriverManager {

    public abstract WebDriver getDriver();

    public abstract WebDriver getDriver(DriverService service);

    public abstract WebDriver getDriver(Capabilities capabilities);

    public abstract WebDriver getDriver(DriverService service, Capabilities caps);

    public abstract WebDriver getDriver(URL remoteAddress, Capabilities caps);

    protected String getOS() {
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
