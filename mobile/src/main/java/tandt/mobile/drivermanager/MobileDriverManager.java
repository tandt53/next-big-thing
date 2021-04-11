package tandt.mobile.drivermanager;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import tandt.common.exceptions.CommonException;
import tandt.dataprovider.exceptions.PropertiesException;

import java.net.MalformedURLException;

public abstract class MobileDriverManager {

    public static ThreadLocal<AppiumDriver<WebElement>> driver = new ThreadLocal<>();

    public AppiumDriver<WebElement> getDriver() {
        if (driver.get() == null) {
            try {
                initDriver();
            } catch (CommonException | MalformedURLException | PropertiesException e) {
                e.printStackTrace();
            }
        }
        return driver.get();
    }

    public abstract AppiumDriver<WebElement> initDriver() throws CommonException, MalformedURLException, PropertiesException;

}
