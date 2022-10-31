package onboarding.mobile.test.test.gesture;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Injector;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import onboarding.commontest.TestContext;
import onboarding.commontest.TestModule;
import onboarding.mobile.MobileModule;
import onboarding.mobile.drivermanager.DriverManager;
import onboarding.mobile.gesture.IosGesture;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.time.Duration;

@Guice(modules = {TestModule.class, MobileModule.class})
public class IosGestureTest {
    @Inject
    private Injector injector;

    @Inject
    private DriverManager driverManager;

    private AppiumDriver driver;

    private IosGesture iosGesture;

    @BeforeMethod
    public void setUp() {
        // TODO this path is not able run on ci runner
        String path = System.getProperty("user.dir") + "/../data/apps/UICatalog.app";
        TestContext.getInstance().getConfiguration().add("onboarding.appium.app", path);
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        iosGesture = injector.getInstance(IosGesture.class);
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quit();
    }

    @Test
    public void testScroll(){
        iosGesture.scrollDown();
        iosGesture.scrollUp();

        WebElement container = driver.findElement(AppiumBy.iOSNsPredicateString("type == \"XCUIElementTypeTable\""));
        String id = ((RemoteWebElement)container).getId();

        ((JavascriptExecutor) driver).executeScript("mobile: scroll", ImmutableMap.of(
                "direction", "down",
                "elementId", id
        ));

        ((JavascriptExecutor) driver).executeScript("mobile: scroll", ImmutableMap.of(
                "direction", "up",
                "elementId", id
        ));
    }



}
