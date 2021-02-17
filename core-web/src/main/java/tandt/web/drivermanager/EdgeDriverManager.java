package tandt.web.drivermanager;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager extends DriverManager{

    protected static String KEY_EDGE = "webdriver.edge.driver";

    @Override
    public WebDriver initDriver() {
        Capability caps = CapabilityManager.loadCaps();
        System.setProperty(KEY_EDGE, caps.getValue(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new EdgeDriver(new MutableCapabilities(caps.getMap())));
        return getDriver();
    }

}
