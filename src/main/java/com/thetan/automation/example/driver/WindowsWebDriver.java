package com.thetan.automation.example.driver;

import com.thetan.automation.example.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by thetan.do on 12/24/2016.
 */
public class WindowsWebDriver extends BaseWebDriver {
    private WebDriver driver = null;

    public WebDriver getDriver(String browser) {
        if (browser.equals(Constants.BROWSER_CHROME))
            chromeDriver();
        if (browser.equals(Constants.BROWSER_FIREFOX))
            firefoxDriver();
        if (browser.equals(Constants.BROWSER_IE))
            edgeDriver();
        return driver;
    }

    protected void firefoxDriver() {
        try {
            System.out.println("Windows Firefox Driver");
            File classpathRoot = new File(System.getProperty("user.dir"));
            File driverDir = new File(classpathRoot, "./res/driver/");
            File driverExe = new File(driverDir.getCanonicalPath(), "./win/geckodriver.exe");
            firefoxDriverPath = driverExe.getCanonicalPath();
            System.setProperty(KEY_FIREFOX, firefoxDriverPath);
            driver = new FirefoxDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void chromeDriver() {
        try {
            System.out.println("Windows Chrome Driver");
            File classpathRoot = new File(System.getProperty("user.dir"));
            File driverDir = new File(classpathRoot, "./res/driver/");
            File driverExe = new File(driverDir.getCanonicalPath(), "./win/chromedriver.exe");
            chromeDriverPath = driverExe.getCanonicalPath();
            System.setProperty(KEY_CHROME, chromeDriverPath);
            driver = new ChromeDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void edgeDriver() {
        try {
            System.out.println("Windows Edge Driver");
            File classpathRoot = new File(System.getProperty("user.dir"));
            File driverDir = new File(classpathRoot, "./res/driver/");
            File driverExe = new File(driverDir.getCanonicalPath(), "./win/MicrosoftWebDriver.exe");
            ieDriverPath = driverExe.getCanonicalPath();
            System.setProperty(KEY_EDGE, ieDriverPath);
            driver = new EdgeDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void safariDriver() {

    }
    protected void androidDriver() {

    }

    protected void iOsDriver() {

    }
}
