package onboarding.mobile.drivermanager;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import onboarding.commontest.Prop;
import onboarding.mobile.drivermanager.option.DriverOptionFilter;
import onboarding.ui.exception.DriverInitException;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager extends DriverManager {

    @Inject
    private DriverOptionFilter optionFilter;

    @Inject
    @Prop("onboarding.appium.remote.url")
    private String remoteUrl;

    @Override
    public AppiumDriver initDriver() {
        try {
            URL url = new URL(remoteUrl);
            driver = new AndroidDriver(url, new UiAutomator2Options(optionFilter.filter()));
            return driver;
        } catch (MalformedURLException e) {
            throw new DriverInitException("Unable to init AndroidDriver", e.getCause());
        }
    }
}
