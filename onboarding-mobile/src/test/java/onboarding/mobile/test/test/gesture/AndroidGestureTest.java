package onboarding.mobile.test.test.gesture;

import com.google.inject.Inject;
import com.google.inject.Injector;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import onboarding.commontest.TestContext;
import onboarding.commontest.TestModule;
import onboarding.mobile.MobileModule;
import onboarding.mobile.drivermanager.DriverManager;
import onboarding.mobile.gesture.AndroidGesture;
import onboarding.mobile.test.test.Utils;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.time.Duration;

@Guice(modules = {TestModule.class, MobileModule.class})
public class AndroidGestureTest {

    private AndroidGesture androidGesture;

    @Inject
    private DriverManager driverManager;

    private AppiumDriver driver;

    @Inject
    private Injector injector;

    @BeforeMethod
    public void setUp() {
        // TODO this path is not able run on ci runner
        String path = System.getProperty("user.dir") + "/../data/apps/ApiDemos-debug.apk";
        TestContext.getInstance().getConfiguration().add("onboarding.appium.app", path);
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        androidGesture = injector.getInstance(AndroidGesture.class);
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quit();
    }

    @Test
    public void testScroll() {
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Views\")")).click();
        Utils.delay(2000);
        while (androidGesture.scrollDown()) {
        }
        Utils.delay(2000);
        while (androidGesture.scrollUp()) {
        }
        Utils.delay(2000);
    }

    @Test
    public void testDragAndDrop(){
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Views\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Drag and Drop\")")).click();
        WebElement source = driver.findElement(AppiumBy.id("drag_dot_1"));
        WebElement destination = driver.findElement(AppiumBy.id("drag_dot_2"));

        Rectangle rect1 = source.getRect();
        int startX = rect1.x + rect1.width/2;
        int startY = rect1.y + rect1.height/2;

        Rectangle rect2 = destination.getRect();
        int endX = rect2.x + rect2.width/2;
        int endY = rect2.y + rect2.height/2;
        androidGesture.dragAndDrop(startX, startY, endX, endY);
    }

}

