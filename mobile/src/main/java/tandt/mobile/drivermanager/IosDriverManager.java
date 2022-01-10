package tandt.mobile.drivermanager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import tandt.common.configurations.ContextImpl;
import tandt.common.configurations.capability.Capability;
import ui.exception.DriverInitException;

import java.net.MalformedURLException;
import java.net.URL;

public class IosDriverManager extends DriverManager {

    @Override
    public AppiumDriver initDriver() {
        Capability caps = ContextImpl.createInstance().getCapability();
        try {
            URL url = new URL((String) caps.get(Constants.CAPABILITY_SERVER_URL));
            caps.remove(Constants.CAPABILITY_SERVER_URL);
            driver.set(new AndroidDriver(url, new XCUITestOptions(caps.getCapabilities())));
            return getDriver();
        } catch (MalformedURLException e) {
            throw new DriverInitException("Unable to init IosDriver", e.getCause());
        }
    }
}
