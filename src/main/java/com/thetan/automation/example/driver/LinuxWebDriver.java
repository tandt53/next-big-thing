package com.thetan.automation.example.driver;

import com.thetan.automation.example.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by thetan.do on 12/24/2016.
 */
public class LinuxWebDriver extends AbstractWebDriver {
    private WebDriver driver = null;

    public WebDriver getDriver(String browser) {
        if (browser.equals(Constants.BROWSER_CHROME))
            chromeDriver();
        if (browser.equals(Constants.BROWSER_FIREFOX))
            firefoxDriver();
        return driver;
    }

    protected void firefoxDriver()  {
        try {
            System.out.println("Linux Firefox Driver");
            File classpathRoot = new File(System.getProperty("user.dir"));
            File driverDir = new File(classpathRoot, "./res/driver/");
            File driverExe = new File(driverDir.getCanonicalPath(), "./lin/geckodriver");
            firefoxDriverPath = driverExe.getAbsolutePath();
            System.setProperty(KEY_FIREFOX, firefoxDriverPath);
            driver = new FirefoxDriver();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    protected void chromeDriver() {
        try {
            System.out.println("L Chrome Driver");
            File classpathRoot = new File(System.getProperty("user.dir"));
            File driverDir = new File(classpathRoot, "./res/driver/");
            File driverExe = new File(driverDir.getCanonicalPath(), "./lin/chromedriver");
            chromeDriverPath =  driverExe.getAbsolutePath();
            System.setProperty(KEY_CHROME, chromeDriverPath);
            driver = new ChromeDriver();
        } catch (IOException e){
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
