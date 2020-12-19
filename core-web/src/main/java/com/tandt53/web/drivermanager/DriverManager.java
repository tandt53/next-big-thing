package com.tandt53.web.drivermanager;

import com.tandt53.common.exceptions.CommonException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;

public abstract class DriverManager {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public abstract WebDriver initDriver() throws MalformedURLException, CommonException;


    /**
     * This function will take screenshot
     *
     * @param fileWithPath
     * @throws Exception
     */
    public void takeSnapShot(String fileWithPath) {
        TakesScreenshot scrShot = ((TakesScreenshot) driver.get());
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
    }
}
