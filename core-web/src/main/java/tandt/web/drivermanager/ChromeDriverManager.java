package tandt.web.drivermanager;

import com.google.inject.Inject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tandt.web.capability.ICapability;
import tandt.web.capability.ICapabilityManagerService;

public class ChromeDriverManager extends DriverManager {

    @Inject
    private ICapabilityManagerService service;

    protected static String KEY_CHROME = "webdriver.chrome.driver";

    @Override
    public WebDriver initDriver()  {
        ICapability caps = service.getCapability();
        System.setProperty(KEY_CHROME, caps.getValue(Constants.CAPABILITY_DRIVER_PATH).toString());
        driver.set(new ChromeDriver(new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
