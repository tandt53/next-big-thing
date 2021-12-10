package tandt.web.drivermanager;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import tandt.common.configurations.ContextImpl;
import tandt.common.configurations.capability.Capability;

public class FirefoxDriverManager extends DriverManager {

    protected static final String KEY_FIREFOX = "webdriver.gecko.driver";

    @Override
    public WebDriver initDriver() {
        Capability caps = ContextImpl.createInstance().getCapability();
        System.setProperty(KEY_FIREFOX, (String) caps.get(Constants.CAPABILITY_DRIVER_PATH));
        FirefoxOptions options = new FirefoxOptions();
        options.merge(new MutableCapabilities(caps.getCapabilities()));
        driver.set(new FirefoxDriver(options));
        return getDriver();
    }

}
