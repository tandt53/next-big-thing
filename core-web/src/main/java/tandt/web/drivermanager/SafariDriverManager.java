package tandt.web.drivermanager;

import com.google.inject.Inject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import tandt.web.capability.Capability;
import tandt.web.capability.CapabilityService;

public class SafariDriverManager extends DriverManager {

    @Inject
    private Capability caps;

    protected static String KEY_SAFARI = "please add safari driver here";

    @Override
    public WebDriver initDriver() {
        System.setProperty(KEY_SAFARI, caps.getValue(Constants.CAPABILITY_DRIVER_PATH));
        driver.set(new SafariDriver(new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
