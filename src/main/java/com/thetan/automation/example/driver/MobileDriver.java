package com.thetan.automation.example.driver;

import com.thetan.automation.example.utils.Constants;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MobileDriver extends AbstractWebDriver {
    private WebDriver driver = null;

    public WebDriver getDriver(String browser) throws IOException {
        if (browser.equals(Constants.MOBILE_ANDROID)) {
            androidDriver();
        }

        if (browser.equals(Constants.MOBILE_IOS)) {
            iOsDriver();
        }
        return null;
    }

    protected void firefoxDriver() throws IOException {

    }

    protected void chromeDriver() throws IOException {

    }

    protected void edgeDriver() {

    }

    protected void safariDriver() {

    }

    protected void androidDriver() {
        try {
            File classpathRoot = new File(System.getProperty("user.dir"));
            File appDir = new File(classpathRoot, "./apps/");
            File app = new File(appDir.getCanonicalPath(), "ebxgo.apk");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "Android");
            capabilities.setCapability("app", app.getAbsolutePath());
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void iOsDriver() {

    }
}
