package tandt.mobile.drivermanager;

import tandt.dataprovider.exceptions.PropertiesException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class IosDriverManager extends MobileDriverManager{
    @Override
    public AppiumDriver<WebElement> initDriver() throws PropertiesException, MalformedURLException {
        Capability caps = CapabilityManager.loadCaps();
        URL url = new URL(caps.getValue(Constants.CAPABILITY_SERVER_URL).toString());
        caps.removeInfo(Constants.CAPABILITY_SERVER_URL);
        driver.set(new IOSDriver<WebElement>(url, new DesiredCapabilities(caps.getMap())));
        return getDriver();
    }
}
