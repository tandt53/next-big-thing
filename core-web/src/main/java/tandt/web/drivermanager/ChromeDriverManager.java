package tandt.web.drivermanager;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import tandt.commontest.TestContext;
import tandt.commontest.configuration.Configuration;

public class ChromeDriverManager extends DriverManager {

    protected static String KEY_CHROME = "webdriver.chrome.driver";

    @Override
    public WebDriver initDriver()  {
        Configuration caps = TestContext.getInstance().getConfiguration();
        System.setProperty(KEY_CHROME, (String) caps.get(Constants.CONFIGURATION_DRIVER_PATH));
        ChromeOptions options = new ChromeOptions();
        options.merge(new MutableCapabilities(caps.getConfigs()));
        driver.set(new ChromeDriver(options));
        return getDriver();
    }

}
