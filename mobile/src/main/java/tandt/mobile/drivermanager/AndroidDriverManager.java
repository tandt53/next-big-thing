package tandt.mobile.drivermanager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import tandt.commontest.TestContext;
import tandt.commontest.configuration.Configuration;
import ui.exception.DriverInitException;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager extends DriverManager {

    @Override
    public AppiumDriver initDriver() {
        Configuration caps = TestContext.getInstance().getConfiguration();
        try {
            URL url = new URL((String) caps.get(Constants.CAPABILITY_SERVER_URL));
            driver = new AndroidDriver(url, new UiAutomator2Options(caps.getConfigs()));
            return driver;
        } catch (MalformedURLException e) {
            throw new DriverInitException("Unable to init AndroidDriver", e.getCause());
        }
    }
}
