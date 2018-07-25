package com.tandt.automation.example.driver;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by thetan.do on 12/24/2016.
 */
public abstract class BaseWebDriver {
	
	File classpathRoot = new File(System.getProperty("user.dir"));
	File driverDir = new File(classpathRoot, "./res/driver");
	
    // keys
    protected static String KEY_CHROME = "webdriver.chrome.driver";
    protected static String KEY_FIREFOX = "webdriver.gecko.driver";
    protected static String KEY_EDGE = "webdriver.edge.driver";
    protected static String KEY_SAFARI = "please add safari driver here";

    // values
    protected String chromeDriverPath;
    protected String firefoxDriverPath;
    protected String ieDriverPath;
    protected String safariDriverPath;

    public abstract WebDriver getDriver(String browser) throws IOException;

    protected abstract void firefoxDriver() throws IOException;

    protected abstract void chromeDriver() throws IOException;

    protected abstract void edgeDriver();

    protected abstract void safariDriver();

    protected abstract void androidDriver();

    protected abstract void iOsDriver();

}
