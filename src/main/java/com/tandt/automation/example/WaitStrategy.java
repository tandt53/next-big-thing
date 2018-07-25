package com.tandt.automation.example;


import org.openqa.selenium.support.ui.WebDriverWait;

import com.tandt.automation.example.driver.Driver;

public interface WaitStrategy {
	
	static long DEFAULT_TIMEOUT = 30;
	
	default WebDriverWait getWait() {
		return new WebDriverWait(Driver.initWebDriver(), DEFAULT_TIMEOUT);
	}
	
	default WebDriverWait getWait(long timeout) {
		return new WebDriverWait(Driver.initWebDriver(), timeout);
	}
	
	
	
	
}
