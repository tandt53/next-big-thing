package com.tandt.automation.example.driver;

import com.tandt.automation.example.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by thetan.do on 12/24/2016.
 */
public class MacWebDriver extends BaseWebDriver {
    private WebDriver driver = null;


    public WebDriver getDriver(String browser) {
        if (browser.equals(Constants.BROWSER_CHROME))
            chromeDriver();
        if (browser.equals(Constants.BROWSER_FIREFOX))
            firefoxDriver();
        return driver;
    }
    protected void firefoxDriver() {
    }

    protected void chromeDriver() {
        try {
            System.out.println("L Chrome Driver");
            File driverExe = new File(driverDir.getCanonicalPath(), "/mac/chromedriver");
            chromeDriverPath = driverExe.getAbsolutePath();
            System.setProperty(KEY_CHROME, chromeDriverPath);
            driver = new ChromeDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void edgeDriver() {
    }

    protected void safariDriver() {
    }

    protected void androidDriver() {

    }

    protected void iOsDriver() {

    }
}
