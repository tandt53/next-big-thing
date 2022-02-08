package tandt.web.drivermanager;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import tandt.commontest.TestContext;
import tandt.commontest.configuration.Configuration;

public class SafariDriverManager extends DriverManager {

    protected static final String KEY_SAFARI = "please add safari driver here";

    @Override
    public WebDriver initDriver() {
        Configuration caps = TestContext.getInstance().getConfiguration();        System.setProperty(KEY_SAFARI, (String) caps.get(Constants.CONFIGURATION_DRIVER_PATH));
        SafariOptions options = new SafariOptions();
        options.merge(new MutableCapabilities(caps.getConfigs()));
        driver.set(new SafariDriver(options));
        return getDriver();
    }

}
