package com.tandt.automation.example.driver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tandt.automation.example.driver.provider.WebDriverInjector;
import com.tandt.automation.example.driver.provider.WebDriverSelector;
import com.tandt.automation.example.utils.Constants;
import com.tandt.automation.example.utils.LoadConfig;

public class DriverManager {

	private static WebDriver driver;

	public static WebDriver initDriver() {

		if (driver != null) {
			return driver;
		}

		String browser = LoadConfig.getConfigBrowser();
		try {
			driver = setDriver(browser);
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return driver;
	}

	private static WebDriver setDriver(String browser) throws IOException {
		Injector injector = Guice.createInjector(new WebDriverInjector());
		WebDriverSelector driverSelector = injector.getInstance(WebDriverSelector.class);
		return driverSelector.getDriver(browser);
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void takeScreenShot(String imgName) {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(srcFile, new File(Constants.REPORT_PATH + imgName + ".png"));
			System.out.println("Screenshot is save in " + Constants.REPORT_PATH + imgName + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
