package tandt.mobile.drivermanager;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import tandt.dataprovider.exceptions.PropertiesException;
import tandt.mobile.capability.Capability;
import tandt.mobile.capability.CapabilityService;

import java.net.MalformedURLException;
import java.net.URL;

public class IosDriverManager extends MobileDriverManager {
    @Inject
    private CapabilityService service;

    @Override
    public AppiumDriver<WebElement> initDriver() throws PropertiesException, MalformedURLException {
        Capability caps = service.getCapability();
        URL url = new URL(caps.get(Constants.CAPABILITY_SERVER_URL));
        caps.remove(Constants.CAPABILITY_SERVER_URL);
        driver.set(new AndroidDriver<>(url, new DesiredCapabilities(caps.getCapabilities())));
        return getDriver();
    }
}
