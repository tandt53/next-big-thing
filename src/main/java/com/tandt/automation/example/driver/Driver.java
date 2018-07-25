package com.tandt.automation.example.driver;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tandt.automation.example.driver.provider.WebDriverInjector;
import com.tandt.automation.example.driver.provider.WebDriverSelector;
import com.tandt.automation.example.utils.LoadConfig;

public class Driver {

	public static WebDriver driver;

	public static WebDriver initWebDriver() {

		if (driver != null) {
			return driver;
		}
		
		String browser = LoadConfig.getConfigBrowser();
		try {
			driver = getDriver(browser);
			driver.manage().window().maximize();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return driver;
	}

	private static WebDriver getDriver(String browser) throws IOException {
		Injector injector = Guice.createInjector(new WebDriverInjector());
		WebDriverSelector driverSelector = injector.getInstance(WebDriverSelector.class);
		return driverSelector.getDriver(browser);
	}
}
