package com.tandt.automation.web.drivermanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RemoteDriverManager extends DriverManager {
    static String KEY_SAFARI = "webdriver.safari.driver";
    static String KEY_CHROME = "webdriver.chrome.driver";
    static String KEY_FIREFOX = "webdriver.gecko.driver";
    static String KEY_EDGE = "webdriver.edge.driver";
    RemoteWebDriver remoteWebDriver;
    private static Map<String, String> driverExeFileMap;

    static {
        driverExeFileMap = new HashMap<>();
        driverExeFileMap.put(Constants.OS_LIN, "chromedriver");
        driverExeFileMap.put(Constants.OS_MAC, "chromedriver");
        driverExeFileMap.put(Constants.OS_WIN, "chromedriver.exe");
    }

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
        switch (browser) {
            case "safari":
                System.setProperty(KEY_SAFARI, "/usr/bin/safaridriver");
                break;
            case "chrome":
                String os = currentOS();
                File driverExe = new File(System.getProperty("user.dir"), "/res/driver/" + os + "/" + driverExeFileMap.get(os));
                System.setProperty(KEY_CHROME, driverExe.getAbsolutePath());
                break;
            case "firefox":
                os = currentOS();
                driverExe = new File(System.getProperty("user.dir"), "/res/driver/" + os + "/" + driverExeFileMap.get(os));
                System.setProperty(KEY_FIREFOX, driverExe.getAbsolutePath());
                break;
            case "edge":
                os = currentOS();
                driverExe = new File(System.getProperty("user.dir"), "/res/driver/" + os + "/" + driverExeFileMap.get(os));
                System.setProperty(KEY_EDGE, driverExe.getAbsolutePath());
                break;
        }
        caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setBrowserName(browser);
        remoteWebDriver = new RemoteWebDriver(remoteAddress, caps);
        return remoteWebDriver;
    }
}
