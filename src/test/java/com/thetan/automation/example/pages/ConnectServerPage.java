//package com.thetan.automation.example.pages;
//
//import org.openqa.selenium.Keys;
//import org.testng.util.Strings;
//
//import com.thetan.automation.example.BaseMobilePage;
//
//public class ConnectServerPage extends BaseMobilePage{
//	
//	public ConnectServerPage() {
//		super();
//	}
//	
//	private static final String XPATH_TITLE = "//page-connect-server//ion-header//ion-toolbar//ion-title";
//	private static final String XPATH_BTN_CONNECT = "//*[contains(@class,'button')][contains(@type,'submit')]";
//	private static final String XPATH_BTN_SAVE = "//button/*[normalize-space()='Save']";
//	private static final String XPATH_USER = "//*[contains(@class,'text-input-ios')][contains(@name,'username')]";
//	private static final String XPATH_PROTOCOL = "//ion-toggle";
//	private static final String XPATH_ERROR_MESSAGE = "//ion-label[contains(@class,'label-notif-err')]";
//
//	private static final String XPATH_SAVED_SERVER = "//ion-label[normalize-space(text())='Saved server configurations']";
//
//	public void closePage() {
//
//	}
//
//	public void enterServerName(String serverName) {
//		String xPathName = "//input[contains(@class,'text-input')][contains(@name,'name')]";
//		if (Strings.isNullOrEmpty(serverName)) {
//			waitTypeAndTab(xPathName, "1");
//			getElement(xPathName).sendKeys(Keys.BACK_SPACE);
//			getElement(xPathName).sendKeys(Keys.TAB);
//		} else
//			waitTypeAndTab(xPathName, serverName);
//	}
//
//	public void enterServerAddress(String serverAddress) {
//		String xPathAddress = "//input[contains(@class,'text-input')][contains(@name,'address')]";
//		if (Strings.isNullOrEmpty(serverAddress)) {
//			waitTypeAndTab(xPathAddress, "1");
//			waitAbit(1000);
//			getElement(xPathAddress).sendKeys(Keys.BACK_SPACE);
//			getElement(xPathAddress).sendKeys(Keys.TAB);
//		} else
//			waitTypeAndTab(xPathAddress, serverAddress);
//	}
//}
