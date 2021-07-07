package tandt.mobile.drivermanager;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public abstract class DriverManager {

    public ThreadLocal<AppiumDriver<WebElement>> driver = new ThreadLocal<>();

    public AppiumDriver<WebElement> getDriver() {
        if(driver.get() ==null || driver.get().getSessionId().toString().isEmpty()){
            initDriver();
        }
        return driver.get();
    }

    public abstract AppiumDriver<WebElement> initDriver() ;

}
