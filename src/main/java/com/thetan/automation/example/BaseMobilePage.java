//package com.thetan.automation.example;
//
//import java.io.IOException;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import com.thetan.automation.example.driver.Driver;
//
//import io.appium.java_client.AppiumDriver;
//
//public class BaseMobilePage {
//	public static AppiumDriver<WebElement> driver;
//	public static final String MOBILE_ANDROID = "Android";
//	public static final String MOBILE_IOS = "iOS";
//
//	/**
//	 * COMMON METHOD FOR ELEMENT
//	 **/
//
//	public BaseMobilePage() {
//		getDriver();
//	}
//	private void getDriver() {
//		if (driver == null) {
//			driver = Driver.initMobileDriver();
//			PageFactory.initElements(driver, this);
//		}
//	}
//
//	public void typeAndEnter(String _xPath, String _value) {
//		getDriver();
//		type(_xPath, _value);
//		getElement(_xPath).sendKeys(Keys.ENTER);
//		waitAbit(1000);
//	}
//
//	private void type(String xPath, String value) {
//		getDriver();
//		highlightElement(xPath);
//		getElement(xPath).clear();
//		getElement(xPath).sendKeys(value);
//	}
//
//	public WebElement getElement(String xPath) {
//		getDriver();
//		highlightElement(xPath);
//		waitForVisibilityOfElement(xPath);
//		return driver.findElementByXPath(xPath);
//	}
//
//	public void waitAbit(long millis) {
//		try {
//			Thread.sleep(millis);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void highlightElement(String xPath) {
//		getDriver();
//		try {
//			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'",
//					driver.findElementByXPath(xPath));
//		} catch (Exception e) {
//		}
//	}
//
//	// Display on UI
//	public void waitForVisibilityOfElement(String xPath) {
//		getDriver();
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
//	}
//	
//	public void waitTypeAndTab(String xPath, String value) {
//		getDriver();
//		getElement(xPath).clear();
//		waitAbit(1000);
//		type(xPath, value);
//		getElement(xPath).sendKeys(Keys.TAB);
//		waitFor(ExpectedConditions.attributeToBe(getElement(xPath + "/parent::ebx-ion-input"), "ng-reflect-model",
//				value));
//	}
//	
//	/** START COMMON WAIT **/
//	public Boolean waitFor(ExpectedCondition<Boolean> condition) {
//		getDriver();
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		return wait.until(condition);
//	}
//	
//}
