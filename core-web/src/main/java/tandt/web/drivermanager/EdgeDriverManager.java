package tandt.web.drivermanager;

import com.google.inject.Inject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import tandt.web.capability.Capability;
import tandt.web.capability.CapabilityService;

public class EdgeDriverManager extends DriverManager{

    @Inject
    private CapabilityService service;

    protected static String KEY_EDGE = "webdriver.edge.driver";

    @Override
    public WebDriver initDriver() {
        Capability caps = service.getCapability();
        System.setProperty(KEY_EDGE, caps.getValue(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new EdgeDriver(new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
