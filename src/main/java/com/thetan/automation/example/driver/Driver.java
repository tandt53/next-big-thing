package com.thetan.automation.example.driver;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thetan.automation.example.driver.provider.WebDriverInjector;
import com.thetan.automation.example.driver.provider.WebDriverSelector;
import com.thetan.automation.example.utils.LoadConfig;

public class Driver {
	
	public static WebDriver initWebDriver() {
		WebDriver driver = null;
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
	
//	@SuppressWarnings("unchecked")
//	public static AppiumDriver<WebElement> initMobileDriver() {
//		try {
//			return BaseMobileDriver.getDriver();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
}
