package light.mobile.drivermanager;

import io.appium.java_client.AppiumDriver;

public abstract class DriverManager {

    protected AppiumDriver driver;

    public AppiumDriver getDriver() {
        if (driver == null || driver.getSessionId() == null) {
            initDriver();
        }
        return driver;
    }

    public abstract AppiumDriver initDriver();

    public void quit() {
        if (driver != null || driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
