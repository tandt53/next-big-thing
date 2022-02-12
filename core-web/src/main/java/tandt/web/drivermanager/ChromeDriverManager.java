package tandt.web.drivermanager;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import tandt.commontest.TestContext;
import tandt.commontest.configuration.Configuration;
import tandt.web.drivermanager.option.DriverOptionFilter;

public class ChromeDriverManager extends DriverManager {

    protected static String KEY_CHROME = "webdriver.chrome.driver";

    @Inject
    private DriverOptionFilter optionFilter;

    @Override
    public WebDriver initDriver()  {
        Configuration caps = TestContext.getInstance().getConfiguration();
        System.setProperty(KEY_CHROME, (String) caps.get(Constants.CONFIGURATION_DRIVER_PATH));
        ChromeOptions options = new ChromeOptions();
        options.merge(this.optionFilter.filter());
        driver = new ChromeDriver(options);
        return driver;
    }

}
