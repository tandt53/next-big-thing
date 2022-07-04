package onboarding.web.drivermanager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public abstract class DriverManager {

    protected static WebDriver driver;

    public WebDriver getDriver() {
        if (driver == null || ((RemoteWebDriver) driver).getSessionId()==null)
            return initDriver();
        return driver;
    }

    public abstract WebDriver initDriver();

    /**
     * This function will take screenshot
     *
     * @param imageName
     * @throws IOException
     */
    public void takeSnapShot(String imageName) throws IOException {
        TakesScreenshot scrShot = (TakesScreenshot) driver;
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(Constants.SCREENSHOT_PATH + "/" + imageName);
        FileUtils.copyFile(srcFile, destFile);
    }
}
