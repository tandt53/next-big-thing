package tandt.web.drivermanager;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import tandt.common.configurations.ContextImpl;
import tandt.common.configurations.capability.Capability;

public class SafariDriverManager extends DriverManager {

    protected static final String KEY_SAFARI = "please add safari driver here";

    @Override
    public WebDriver initDriver() {
        Capability caps = ContextImpl.createInstance().getCapability();
        System.setProperty(KEY_SAFARI, (String) caps.get(Constants.CAPABILITY_DRIVER_PATH));
        driver.set(new SafariDriver(new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
