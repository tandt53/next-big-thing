package com.tandt.automation.example.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tandt.automation.example.utils.Constants;

import java.io.File;
import java.io.IOException;

/**
 * Created by thetan.do on 12/24/2016.
 */
public class LinuxWebDriver extends BaseWebDriver {
	private WebDriver driver = null;
	

	public WebDriver getDriver(String browser) {
		if (browser.equals(Constants.BROWSER_CHROME))
			chromeDriver();
		if (browser.equals(Constants.BROWSER_FIREFOX))
			firefoxDriver();
		return driver;
	}

	protected void firefoxDriver() {
		try {
			System.out.println("Linux Firefox Driver");
			File driverExe = new File(driverDir.getCanonicalPath(), "/lin/geckodriver");
			firefoxDriverPath = driverExe.getAbsolutePath();
			System.setProperty(KEY_FIREFOX, firefoxDriverPath);
			driver = new FirefoxDriver();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void chromeDriver() {
		try {
			System.out.println("L Chrome Driver");
			File driverExe = new File(driverDir.getCanonicalPath(), "/lin/chromedriver");
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
