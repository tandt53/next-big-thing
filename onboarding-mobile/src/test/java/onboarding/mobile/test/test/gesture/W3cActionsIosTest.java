package onboarding.mobile.test.test.gesture;


import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import onboarding.commontest.TestContext;
import onboarding.commontest.TestModule;
import onboarding.mobile.MobileModule;
import onboarding.mobile.drivermanager.DriverManager;
import onboarding.mobile.gesture.Direction;
import onboarding.mobile.gesture.W3cActions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

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
        // TODO this path is not able run on ci runner
        String path = System.getProperty("user.dir") + "/../data/apps/UICatalog.app";
        TestContext.getInstance().getConfiguration().add("onboarding.appium.app", path);
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
    }

    @Test
    public void testMoveToElement() {
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
