package tandt53.mobile.drivermanager;

import tandt53.common.exceptions.CommonException;
import tandt53.dataprovider.exceptions.PropertiesException;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

public abstract class MobileDriverManager {

    protected ThreadLocal<AppiumDriver<WebElement>> driver = new ThreadLocal<>();

    public AppiumDriver<WebElement> getDriver() {
        return driver.get();
    }

    public abstract AppiumDriver<WebElement> initDriver() throws CommonException, MalformedURLException, PropertiesException;

}
