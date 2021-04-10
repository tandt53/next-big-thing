package tandt.web.drivermanager;

import com.google.inject.Inject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.capability.Capability;
import ui.capability.CapabilityService;

public class ChromeDriverManager extends DriverManager {

    @Inject
    private CapabilityService service;

    protected static String KEY_CHROME = "webdriver.chrome.driver";

    @Override
    public WebDriver initDriver()  {
        Capability caps = service.getCapability();
        System.setProperty(KEY_CHROME, caps.get(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new ChromeDriver(new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
