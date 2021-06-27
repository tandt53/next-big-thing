package tandt.web.drivermanager;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ui.capability.Capability;
import ui.capability.CapabilityService;

public class FirefoxDriverManager extends DriverManager {

    @Inject
    @Named("web")
    private CapabilityService service;

    protected static final String KEY_FIREFOX = "webdriver.gecko.driver";

    @Override
    public WebDriver initDriver() {
        Capability caps = service.getCapability();
        System.setProperty(KEY_FIREFOX, (String) caps.get(Constants.CAPABILITY_DRIVER_PATH));
        driver.set(new FirefoxDriver(new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
