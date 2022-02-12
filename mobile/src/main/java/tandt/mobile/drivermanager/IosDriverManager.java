package tandt.mobile.drivermanager;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import tandt.commontest.Prop;
import tandt.commontest.TestContext;
import tandt.commontest.configuration.Configuration;
import tandt.mobile.drivermanager.option.DriverOptionFilter;
import ui.exception.DriverInitException;

import java.net.MalformedURLException;
import java.net.URL;

public class IosDriverManager extends DriverManager {
    @Inject
    private DriverOptionFilter optionFilter;

    @Inject
    @Prop("nbt.appium.remote.url")
    private String remoteUrl;

    @Override
    public AppiumDriver initDriver() {
        try {
            URL url = new URL(remoteUrl);
            driver = new AndroidDriver(url, new XCUITestOptions(optionFilter.filter()));
            return driver;
        } catch (MalformedURLException e) {
            throw new DriverInitException("Unable to init AndroidDriver", e.getCause());
        }
    }
}
