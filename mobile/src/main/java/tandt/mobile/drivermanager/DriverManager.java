package tandt.mobile.drivermanager;

import io.appium.java_client.AppiumDriver;

public abstract class DriverManager {

    public ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public AppiumDriver getDriver() {
        if(driver.get() ==null || driver.get().getSessionId().toString().isEmpty()){
            initDriver();
        }
        return driver.get();
    }

    public abstract AppiumDriver initDriver() ;

}
