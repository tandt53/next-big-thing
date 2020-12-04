package com.tandt53.automation.web.drivermanager;

import com.tandt53.automation.web.drivermanager.options.CapabilityManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager extends DriverManager{

    protected static String KEY_EDGE = "webdriver.edge.driver";

    @Override
    public WebDriver initDriver() {
        Capabilities caps = CapabilityManager.loadCaps();
        System.setProperty(KEY_EDGE, caps.getCapability(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new EdgeDriver(caps));
        return getDriver();
    }

}
