package com.tandt53.automation.web.drivermanager;

import com.tandt53.automation.common.exceptions.CommonException;
import com.tandt53.automation.web.drivermanager.options.CapabilityManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager {

    protected static String KEY_CHROME = "webdriver.chrome.driver";
//    private static Map<String, String> driverExeFileMap;
//
//    static {
//        driverExeFileMap = new HashMap<>();
//        driverExeFileMap.put(Constants.OS_LIN, "chromedriver");
//        driverExeFileMap.put(Constants.OS_MAC, "chromedriver");
//        driverExeFileMap.put(Constants.OS_WIN, "chromedriver.exe");
//    }

    @Override
    public WebDriver initDriver() throws CommonException {
        Capabilities caps = CapabilityManager.loadCaps();
        System.setProperty(KEY_CHROME, caps.getCapability(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new ChromeDriver(caps));
        return getDriver();
    }

}
