package com.thetan.automation.example.driver;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by thetan.do on 12/24/2016.
 */
public abstract class AbstractWebDriver {
    // keys
    public static String KEY_CHROME = "webdriver.chrome.driver";
    public static String KEY_FIREFOX = "webdriver.gecko.driver";
    public static String KEY_EDGE = "webdriver.edge.driver";
    public static String KEY_SAFARI = "please add safari driver here";

    // values
    public String chromeDriverPath;
    public String firefoxDriverPath;
    public String ieDriverPath;
    public String safariDriverPath;

    public abstract WebDriver getDriver(String browser) throws IOException;

    protected abstract void firefoxDriver() throws IOException;

    protected abstract void chromeDriver() throws IOException;

    protected abstract void edgeDriver();

    protected abstract void safariDriver();

    protected abstract void androidDriver();

    protected abstract void iOsDriver();

}
