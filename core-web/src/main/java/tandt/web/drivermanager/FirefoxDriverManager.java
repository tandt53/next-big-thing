package tandt.web.drivermanager;

import com.google.inject.Inject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tandt.web.capability.ICapability;
import tandt.web.capability.ICapabilityManagerService;

public class FirefoxDriverManager extends DriverManager {

    @Inject
    private ICapabilityManagerService service;

    protected static String KEY_FIREFOX = "webdriver.gecko.driver";

    @Override
    public WebDriver initDriver() {
        ICapability caps = service.getCapability();
        System.setProperty(KEY_FIREFOX, caps.getValue(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new FirefoxDriver(new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
