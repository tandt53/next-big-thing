//package com.thetan.automation.example.driver;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.util.Properties;
//
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import com.google.common.base.Strings;
//import com.thetan.automation.example.utils.LoadConfig;
//
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.ios.IOSDriver;
//
//public class BaseMobileDriver {
//	private static AppiumDriver<WebElement> driver;
//	public static Properties CONFIG = null;
//	public static final String MOBILE_ANDROID = "Android";
//	public static final String MOBILE_IOS = "iOS";
//
//	/**
//	 * 
//	 */
//	public static AppiumDriver<WebElement> getDriver() throws IOException {
//
//		if (driver != null) {
//			return driver;
//		}
//
//		String platformName = System.getProperty("platformname");
//		CONFIG = LoadConfig.loadConfig();
//		if (Strings.isNullOrEmpty(platformName)) {
//			platformName = CONFIG.getProperty("app.platformName");
//		}
//
//		try {
//			if (platformName.equals(MOBILE_ANDROID)) {
//				DesiredCapabilities capabilities = getCapabilities();
//				driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//			} else if (platformName.equals(MOBILE_IOS)) {
//				DesiredCapabilities capabilities = getCapabilitiesIOS();
//				driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//			}
//			return driver;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//
//		}
//
//	}
//
//	public static DesiredCapabilities getCapabilities() throws IOException {
//
//		File classpathRoot = new File(System.getProperty("user.dir"));
//		File appDir = new File(classpathRoot, "./res/apps/");
//
//		String fileApp = System.getProperty("app");
//		String appiumVersion = System.getProperty("appiumversion");
//		String platformName = System.getProperty("platformname");
//		String platformVersion = System.getProperty("platformversion");
//		String deviceName = System.getProperty("device");
//
//		if (Strings.isNullOrEmpty(fileApp)) {
//			fileApp = LoadConfig.getFileApp();
//		}
//		if (Strings.isNullOrEmpty(appiumVersion)) {
//			appiumVersion = LoadConfig.getAppiumVersion();
//		}
//		if (Strings.isNullOrEmpty(platformName)) {
//			platformName = LoadConfig.getPlatformName();
//
//		}
//		if (Strings.isNullOrEmpty(platformVersion)) {
//			platformVersion = LoadConfig.getPlatformVersion();
//		}
//		if (Strings.isNullOrEmpty(deviceName)) {
//			deviceName = LoadConfig.getDeviceName();
//		}
//		File app = new File(appDir.getCanonicalPath(), fileApp);
//
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("appium-version", appiumVersion);
//		capabilities.setCapability("platformName", platformName);
//		capabilities.setCapability("platformVersion", platformVersion);
//		capabilities.setCapability("deviceName", deviceName);
//		capabilities.setCapability("app", app.getAbsolutePath());
//		capabilities.setCapability("autoWebview", true);
//		capabilities.setCapability("nativeWebScreenshot", true);
//		capabilities.setCapability("chromedriverExecutable", "/usr/local/bin/chromedriver");
//		return capabilities;
//	}
//
//	private static DesiredCapabilities getCapabilitiesIOS() throws IOException {
//
//		File classpathRoot = new File(System.getProperty("user.dir"));
//		File appDir = new File(classpathRoot, "./res/apps/");
//
//		String fileApp = System.getProperty("app");
//		if (Strings.isNullOrEmpty(fileApp)) {
//			fileApp = LoadConfig.getFileApp();
//		}
//		String appiumVersion = System.getProperty("appiumversion");
//		String platformName = System.getProperty("platformname");
//		String platformVersion = System.getProperty("platformversion");
//		String deviceName = System.getProperty("device");
//		if (Strings.isNullOrEmpty(appiumVersion)) {
//			appiumVersion = LoadConfig.getAppiumVersion();
//		}
//		if (Strings.isNullOrEmpty(platformName)) {
//			platformName = LoadConfig.getPlatformName();
//		}
//		if (Strings.isNullOrEmpty(platformVersion)) {
//			platformVersion = LoadConfig.getPlatformVersion();
//		}
//		if (Strings.isNullOrEmpty(deviceName)) {
//			deviceName = LoadConfig.getDeviceName();
//		}
//
//		File app = new File(appDir.getCanonicalPath(), fileApp);
//
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("deviceName", deviceName);
//		capabilities.setCapability("platformName", platformName);
//		capabilities.setCapability("platformVersion", platformVersion);
//		capabilities.setCapability("autoLaunch", true);
//		capabilities.setCapability("automationName", "XCUITest");
//		capabilities.setCapability("udid", "auto");
//		capabilities.setCapability("app", app.getAbsolutePath());
//		capabilities.setCapability("showXcodeLog", true);
//		capabilities.setCapability("useNewWDA", false);
//		capabilities.setCapability("browser", "safari");
//		capabilities.setCapability("autoWebview", true);
//
//		capabilities.setCapability("unicodeKeyboard", true);
//		capabilities.setCapability("resetKeyboard", true);
//		capabilities.setCapability("startIWDP", true);
//		capabilities.setCapability("autoAcceptAlerts", true);
//		return capabilities;
//	}
//}
