package light.mobile.drivermanager;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import light.commontest.Prop;
import light.mobile.drivermanager.option.DriverOptionFilter;
import light.ui.exception.DriverInitException;

import java.net.MalformedURLException;
import java.net.URL;

public class IosDriverManager extends DriverManager {
    @Inject
    private DriverOptionFilter optionFilter;

    @Inject
    @Prop("light.appium.remote.url")
    private String remoteUrl;

    @Override
    public AppiumDriver initDriver() {
        try {
            URL url = new URL(remoteUrl);
            driver = new AndroidDriver(url, new XCUITestOptions(optionFilter.filter()));
            return driver;
        } catch (MalformedURLException e) {
            throw new DriverInitException("Unable to init AndroidDriver", e.getCause());
        }
    }
}
