package tandt.mobile.drivermanager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;
import tandt.common.configurations.ContextImpl;
import tandt.common.configurations.capability.Capability;
import ui.exception.DriverInitException;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager extends DriverManager {

    @Override
    public AppiumDriver initDriver() {
        Capability caps = ContextImpl.createInstance().getCapability();
        try {
            URL url = new URL((String) caps.get(Constants.CAPABILITY_SERVER_URL));
            driver.set(new AndroidDriver(url, new UiAutomator2Options(caps.getCapabilities())));
            return getDriver();
        } catch (MalformedURLException e) {
            throw new DriverInitException("Unable to init AndroidDriver", e.getCause());
        }
    }
}
