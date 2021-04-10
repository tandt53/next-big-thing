package tandt.web.drivermanager;

import com.google.inject.Inject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import ui.capability.Capability;
import ui.capability.CapabilityService;

public class SafariDriverManager extends DriverManager {

    @Inject
    private CapabilityService service;

    protected static String KEY_SAFARI = "please add safari driver here";

    @Override
    public WebDriver initDriver() {
        Capability caps = service.getCapability();
        System.setProperty(KEY_SAFARI, caps.get(Constants.CAPABILITY_DRIVER_PATH));
        driver.set(new SafariDriver(new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
