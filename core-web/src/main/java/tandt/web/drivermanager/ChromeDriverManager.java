package tandt.web.drivermanager;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import tandt.common.configurations.ContextImpl;
import tandt.common.configurations.capability.Capability;

public class ChromeDriverManager extends DriverManager {


    protected static String KEY_CHROME = "webdriver.chrome.driver";

    @Override
    public WebDriver initDriver()  {
        Capability caps = ContextImpl.createInstance().getCapability();
        System.setProperty(KEY_CHROME, (String) caps.get(Constants.CAPABILITY_DRIVER_PATH));
        ChromeOptions options = new ChromeOptions();
        options.merge(new MutableCapabilities(caps.getCapabilities()));
        driver.set(new ChromeDriver(options));
        return getDriver();
    }

}
