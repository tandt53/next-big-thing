package tandt.web.drivermanager;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import tandt.commontest.TestContext;
import tandt.commontest.configuration.Configuration;

public class EdgeDriverManager extends DriverManager{

    private static String KEY_EDGE = "webdriver.edge.driver";

    @Override
    public WebDriver initDriver() {
        Configuration caps = TestContext.getInstance().getConfiguration();
        System.setProperty(KEY_EDGE, (String) caps.get(Constants.CONFIGURATION_DRIVER_PATH));
        EdgeOptions options = new EdgeOptions();
        options.merge(new MutableCapabilities(caps.getConfigs()));
        driver.set(new EdgeDriver(options));
        return getDriver();
    }

}
