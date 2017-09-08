package com.thetan.automation.example.driver;

import com.thetan.automation.example.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by thetan.do on 12/24/2016.
 */
public class LinuxWebDriver extends AbstractWebDriver {
    WebDriver driver = null;

    public WebDriver getDriver(String browser) {
        if (browser.equals(Constants.BROWSER_CHROME))
            chromeDriver();
        if (browser.equals(Constants.BROWSER_FIREFOX))
            firefoxDriver();
        return driver;
    }

    protected void firefoxDriver() {
        System.out.println("Windows Firefox Driver");
        firefoxDriverPath = new String(".//res//driver//lin//geckodriver.exe");
        System.setProperty(KEY_FIREFOX, firefoxDriverPath);
        driver = new FirefoxDriver();
    }

    protected void chromeDriver() {
        System.out.println("Windows Chrome Driver");
        chromeDriverPath = new String(".//res//driver//win//chromedriver.exe");
        System.setProperty(KEY_CHROME, chromeDriverPath);
        driver = new ChromeDriver();
    }

    protected void edgeDriver() {
    }

    protected void safariDriver() {
    }
}
