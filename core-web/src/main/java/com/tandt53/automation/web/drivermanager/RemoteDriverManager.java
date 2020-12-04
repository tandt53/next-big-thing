package com.tandt53.automation.web.drivermanager;

import com.tandt53.automation.common.exceptions.CommonException;
import com.tandt53.automation.common.Utils;
import com.tandt53.automation.web.drivermanager.options.CapabilityManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverManager extends DriverManager {
//    private static Map<String, String> driverExeFileMap;
//
//    static {
//        driverExeFileMap = new HashMap<>();
//        driverExeFileMap.put(Constants.OS_LIN, "chromedriver");
//        driverExeFileMap.put(Constants.OS_MAC, "chromedriver");
//        driverExeFileMap.put(Constants.OS_WIN, "chromedriver.exe");
//    }

    @Override
    public WebDriver initDriver() throws CommonException, MalformedURLException {
        Capabilities caps = CapabilityManager.loadCaps();
        String url = Utils.parse(caps.getCapability(Constants.CAPABILITY_REMOTE_HOST).toString());
        driver.set(new RemoteWebDriver(new URL(url), caps));
        return getDriver();
    }

}
