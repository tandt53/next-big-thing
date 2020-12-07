package com.tandt53.automation.web.drivermanager;

import com.tandt53.automation.common.exceptions.CommonException;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public abstract class DriverManager {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver(){
        return driver.get();
    }

    public abstract WebDriver initDriver() throws MalformedURLException, CommonException;

}
