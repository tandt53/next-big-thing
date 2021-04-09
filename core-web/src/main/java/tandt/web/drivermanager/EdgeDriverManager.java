package tandt.web.drivermanager;

import com.google.inject.Inject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import tandt.web.capability.Capability;
import tandt.web.capability.CapabilityService;

public class EdgeDriverManager extends DriverManager{

    @Inject
    private Capability caps;

    protected static String KEY_EDGE = "webdriver.edge.driver";

    @Override
    public WebDriver initDriver() {
        System.setProperty(KEY_EDGE, caps.getValue(Constants.CAPABILITY_DRIVER_PATH));
        driver.set(new EdgeDriver(new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
