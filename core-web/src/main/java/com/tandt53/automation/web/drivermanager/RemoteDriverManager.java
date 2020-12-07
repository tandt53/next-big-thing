package com.tandt53.automation.web.drivermanager;

import com.tandt53.automation.common.exceptions.CommonException;
import com.tandt53.automation.web.drivermanager.options.Capability;
import com.tandt53.automation.web.drivermanager.options.CapabilityManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public class RemoteDriverManager extends DriverManager {

    @Override
    public WebDriver initDriver() throws CommonException, MalformedURLException {
        Capability caps = CapabilityManager.loadCaps();
        driver.set(new RemoteWebDriver(new MutableCapabilities(caps.getMap())));
        return getDriver();
    }

}
