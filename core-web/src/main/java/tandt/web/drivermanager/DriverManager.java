package tandt.web.drivermanager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public abstract class DriverManager {

    public ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        if (driver.get() == null)
            return initDriver();
        return driver.get();
    }

    public abstract WebDriver initDriver();

    /**
     * This function will take screenshot
     *
     * @param imageName
     * @throws IOException
     */
    public void takeSnapShot(String imageName) throws IOException {
        TakesScreenshot scrShot = (TakesScreenshot) driver.get();
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(Constants.SCREENSHOT_PATH + "/" + imageName);
        FileUtils.copyFile(srcFile, destFile);
    }
}
