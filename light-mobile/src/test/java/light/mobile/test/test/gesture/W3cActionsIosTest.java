package light.mobile.test.test.gesture;


import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import light.commontest.TestContext;
import light.commontest.TestModule;
import light.mobile.MobileModule;
import light.mobile.drivermanager.DriverManager;
import light.mobile.gesture.Direction;
import light.mobile.gesture.W3cActions;

import java.time.Duration;

@Guice(modules = {TestModule.class, MobileModule.class })
public class W3cActionsIosTest {

    @Inject
    private W3cActions w3cActions;

    @Inject
    private DriverManager driverManager;

    private AppiumDriver driver;

    @BeforeTest
    public void setUp() {
        // config ios UICatalog and iOS configuration for appium
        TestContext.getInstance().getConfiguration().add("nbt.appium.app", "/Users/tandt1/projects/next-big-thing/mobile/input/apps/UICatalog.app");
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
    }

    @Test
    public void testMoveToElement() {
//        driver.findElement(AppiumBy.androidUIAutomator("text(\"Views\")")).click();
        delay(2000);
        w3cActions.scroll(Direction.DOWN);
    }


    private int getCenterX(WebElement element){
        return element.getLocation().getX() + element.getRect().getWidth()/2;
    }

    private int getCenterY(WebElement element){
        return element.getLocation().getY() + element.getRect().getHeight()/2;
    }

    private void delay(long timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
