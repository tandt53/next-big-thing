package tandt.web.drivermanager;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import tandt.common.configurations.capability.Capability;
import tandt.common.configurations.capability.CapabilityService;

public class EdgeDriverManager extends DriverManager{

    @Inject
    @Named("web")
    private CapabilityService service;

    private static String KEY_EDGE = "webdriver.edge.driver";

    @Override
    public WebDriver initDriver() {
        Capability caps = service.getCapability();
        System.setProperty(KEY_EDGE, (String) caps.get(Constants.CAPABILITY_DRIVER_PATH));
        EdgeOptions options = new EdgeOptions();
        options.merge(new MutableCapabilities(caps.getCapabilities()));
        driver.set(new EdgeDriver(options));
        return getDriver();
    }

}
