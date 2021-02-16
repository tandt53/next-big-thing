package tandt53.web.drivermanager;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {

    protected static String KEY_FIREFOX = "webdriver.gecko.driver";

    @Override
    public WebDriver initDriver() {
        Capability caps = CapabilityManager.loadCaps();
        System.setProperty(KEY_FIREFOX, caps.getValue(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new FirefoxDriver(new MutableCapabilities(caps.getMap())));
        return getDriver();
    }

}
