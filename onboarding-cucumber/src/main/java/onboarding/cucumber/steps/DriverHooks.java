package onboarding.cucumber.steps;

import com.google.inject.Inject;
import com.google.inject.Injector;
import io.appium.java_client.AppiumDriver;
import io.cucumber.guice.ScenarioScoped;
import onboarding.commontest.Prop;
import onboarding.cucumber.exception.TakeScreenshotException;
import onboarding.mobile.drivermanager.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

@ScenarioScoped
public class DriverHooks {

    @Inject
    private Injector injector;

    @Inject
    @Prop("onboarding.appium.platformName")
    private String platform;

    private WebDriver webDriver;
    private AppiumDriver mobileDriver;

    public void initWeb() {
        onboarding.web.drivermanager.DriverManager manager = injector.getInstance(onboarding.web.drivermanager.DriverManager.class);
        webDriver = manager.getDriver();
    }

    public void initMobile() {
        DriverManager manager = injector.getInstance(DriverManager.class);
        mobileDriver = manager.initDriver();
    }

    public void takeScreenshotMobile(String imagePath) {
        File srcFile = ((TakesScreenshot) mobileDriver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(imagePath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new TakeScreenshotException("There's a problem during take screenshot", e.getCause());
        }
    }

    public byte[] takeScreenshotMobile() {
        return ((TakesScreenshot) mobileDriver).getScreenshotAs(OutputType.BYTES);
    }

    public void closeMobileDriver() {
        mobileDriver.quit();
    }

    public void takeScreenshotWeb(String imagePath) {
        File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(imagePath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new TakeScreenshotException("There's a problem during take screenshot", e.getCause());
        }
    }

    public byte[] takeScreenshotWeb() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    public void closeWebDriver() {
        webDriver.quit();
    }

}
