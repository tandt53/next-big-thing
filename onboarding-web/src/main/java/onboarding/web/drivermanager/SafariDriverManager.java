package onboarding.web.drivermanager;

import onboarding.commontest.TestContext;
import onboarding.commontest.configuration.Configuration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariDriverManager extends DriverManager {


    @Override
    public WebDriver initDriver() {
        Configuration caps = TestContext.getInstance().getConfiguration();
        SafariOptions options = new SafariOptions();
        options.merge(new MutableCapabilities(caps.getConfigs()));
        driver = new SafariDriver(options);
        return driver;
    }

}
