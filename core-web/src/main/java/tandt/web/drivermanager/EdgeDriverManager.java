package tandt.web.drivermanager;

import com.google.inject.Inject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import ui.capability.Capability;
import ui.capability.CapabilityService;

public class EdgeDriverManager extends DriverManager{

    @Inject
    private CapabilityService service;

    private static String KEY_EDGE = "webdriver.edge.driver";

    @Override
    public WebDriver initDriver() {
        Capability caps = service.getCapability();
        System.setProperty(KEY_EDGE, caps.get(Constants.CAPABILITY_DRIVER_PATH));
        driver.set(new EdgeDriver(new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
