package tandt.web.drivermanager;

import com.google.inject.Inject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tandt.web.capability.Capability;
import tandt.web.capability.CapabilityService;

public class FirefoxDriverManager extends DriverManager {

    @Inject
    private CapabilityService service;

    protected static String KEY_FIREFOX = "webdriver.gecko.driver";

    @Override
    public WebDriver initDriver() {
        Capability caps = service.getCapability();
        System.setProperty(KEY_FIREFOX, caps.getValue(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new FirefoxDriver(new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
