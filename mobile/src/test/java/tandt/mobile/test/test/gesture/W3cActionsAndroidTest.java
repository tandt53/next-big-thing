package tandt.mobile.test.test.gesture;


import com.google.inject.Inject;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import tandt.commontest.TestContext;
import tandt.commontest.TestModule;
import tandt.mobile.MobileModule;
import tandt.mobile.drivermanager.DriverManager;
import tandt.mobile.gesture.Direction;
import tandt.mobile.gesture.W3cActions;
import tandt.mobile.test.test.Utils;

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
        TestContext.getInstance().getConfiguration().add("nbt.appium.app", "/Users/tandt1/projects/next-big-thing/mobile/input/apps/ApiDemos-debug.apk");
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
