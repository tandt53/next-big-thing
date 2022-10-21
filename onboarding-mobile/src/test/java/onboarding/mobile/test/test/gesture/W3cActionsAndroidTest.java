package onboarding.mobile.test.test.gesture;


import com.google.inject.Inject;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import onboarding.commontest.TestContext;
import onboarding.commontest.TestModule;
import onboarding.mobile.MobileModule;
import onboarding.mobile.drivermanager.DriverManager;
import onboarding.mobile.gesture.Direction;
import onboarding.mobile.gesture.W3cActions;
import onboarding.mobile.test.test.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.Duration;

@Guice(modules = {TestModule.class, MobileModule.class })
public class W3cActionsAndroidTest {

    @Inject
    private W3cActions w3cActions;

    @Inject
    private DriverManager driverManager;

    private AppiumDriver driver;

    @BeforeMethod
    public void setUp(Method method) {
        // TODO this path is not able run on ci runner
        String path = System.getProperty("user.dir") + "/../data/apps/ApiDemos-debug.apk";
        TestContext.getInstance().getConfiguration().add("onboarding.appium.app", path);
        String name = method.getName();
        TestContext.getInstance().getConfiguration().add("name", name);
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
    }

    @Test
    public void testScroll() {
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Views\")")).click();
        Utils.delay(2000);
        w3cActions.scroll(Direction.DOWN);
    }

    @Test
    public void testDragAndDrop(){
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Views\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Drag and Drop\")")).click();

        WebElement source = driver.findElement(By.id("drag_dot_1"));
        WebElement destination = driver.findElement(By.id("drag_dot_2"));

        int startX = getCenterX(source);
        int startY = getCenterY(source);
        int endX = getCenterX(destination);
        int endY = getCenterY(destination);

        w3cActions.dragAndDrop(startX, startY, endX, endY);
    }

    private int getCenterX(WebElement element){
        return element.getLocation().getX() + element.getRect().getWidth()/2;
    }

    private int getCenterY(WebElement element){
        return element.getLocation().getY() + element.getRect().getHeight()/2;
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quit();
    }

}
