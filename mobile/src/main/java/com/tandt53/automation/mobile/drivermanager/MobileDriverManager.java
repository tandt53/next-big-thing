package com.tandt53.automation.mobile.drivermanager;

import com.tandt53.automation.common.exceptions.CommonException;
import com.tandt53.automation.dataprovider.exceptions.PropertiesException;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

public abstract class MobileDriverManager {

    protected ThreadLocal<AppiumDriver<WebElement>> driver = new ThreadLocal<>();

    public AppiumDriver<WebElement> getDriver() {
        return driver.get();
    }

    public abstract AppiumDriver<WebElement> initDriver() throws CommonException, MalformedURLException, PropertiesException;

}
