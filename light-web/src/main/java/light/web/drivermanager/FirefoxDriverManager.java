package light.web.drivermanager;

import light.commontest.TestContext;
import light.commontest.configuration.Configuration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends DriverManager {

    protected static final String KEY_FIREFOX = "webdriver.gecko.driver";

    @Override
    public WebDriver initDriver() {
        Configuration caps = TestContext.getInstance().getConfiguration();
        System.setProperty(KEY_FIREFOX, (String) caps.get(Constants.CONFIGURATION_DRIVER_PATH));
        FirefoxOptions options = new FirefoxOptions();
        options.merge(new MutableCapabilities(caps.getConfigs()));
        driver = new FirefoxDriver(options);
        return driver;
    }

}
