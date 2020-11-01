package com.tandt53.automation.web.drivermanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FirefoxDriverManager extends DriverManager {

    protected static String KEY_FIREFOX = "webdriver.gecko.driver";
    private static Map<String, String> driverExeFileMap;

    static {
        driverExeFileMap = new HashMap<>();
        driverExeFileMap.put(Constants.OS_LIN, "geckodriver");
        driverExeFileMap.put(Constants.OS_MAC, "geckodriver");
        driverExeFileMap.put(Constants.OS_WIN, "geckodriver.exe");
    }

    @Override
    public WebDriver initDriver() {
        String os = currentOS();
        File driverExe = new File(System.getProperty("user.dir"), "/res/driver/" + os + "/" + driverExeFileMap.get(os));
        System.setProperty(KEY_FIREFOX, driverExe.getAbsolutePath());
        return new FirefoxDriver();
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
