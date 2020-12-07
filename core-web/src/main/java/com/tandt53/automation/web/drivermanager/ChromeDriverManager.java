package com.tandt53.automation.web.drivermanager;

import com.tandt53.automation.common.exceptions.CommonException;
import com.tandt53.automation.web.drivermanager.options.Capability;
import com.tandt53.automation.web.drivermanager.options.CapabilityManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager {

    protected static String KEY_CHROME = "webdriver.chrome.driver";

    @Override
    public WebDriver initDriver() throws CommonException {
        Capability caps = CapabilityManager.loadCaps();
        System.setProperty(KEY_CHROME, caps.getValue(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new ChromeDriver(new MutableCapabilities(caps.getMap())));
        return getDriver();
    }

}
