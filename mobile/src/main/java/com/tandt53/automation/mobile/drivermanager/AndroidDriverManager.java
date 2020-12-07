package com.tandt53.automation.mobile.drivermanager;

import com.tandt53.automation.dataprovider.exceptions.PropertiesException;
import com.tandt53.automation.mobile.drivermanager.options.Capability;
import com.tandt53.automation.mobile.drivermanager.options.CapabilityManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager extends MobileDriverManager {
    @Override
    public AppiumDriver<WebElement> initDriver() throws PropertiesException, MalformedURLException {
        Capability caps = CapabilityManager.loadCaps();
        URL url = new URL(caps.getValue(Constants.CAPABILITY_SERVER_URL).toString());
        caps.removeInfo(Constants.CAPABILITY_SERVER_URL);
        driver.set(new AndroidDriver<WebElement>(url, new DesiredCapabilities(caps.getMap())));
        return getDriver();
    }
}
