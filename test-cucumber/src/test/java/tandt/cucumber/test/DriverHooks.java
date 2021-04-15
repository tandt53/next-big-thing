package tandt.cucumber.test;

import com.google.inject.Inject;
import com.google.inject.Injector;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ScenarioScoped
public class DriverHooks {

    @Inject
    private Injector injector;

    private WebDriver webDriver;
    private AppiumDriver<WebElement> mobileDriver;

    public void iOpenBrowser() {
        webDriver = injector.getInstance(WebDriver.class);
    }

    public void iOpenApplication() {
        mobileDriver = (AppiumDriver<WebElement>) injector.getInstance(MobileDriver.class);
    }
}
