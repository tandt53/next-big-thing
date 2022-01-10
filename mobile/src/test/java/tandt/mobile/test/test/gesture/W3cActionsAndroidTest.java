package tandt.mobile.test.test.gesture;


import com.google.inject.Inject;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import tandt.mobile.MobileModule;
import tandt.mobile.drivermanager.DriverManager;
import tandt.mobile.gesture.Direction;
import tandt.mobile.gesture.W3cActions;

import java.time.Duration;

@Guice(modules = {MobileModule.class })
public class W3cActionsAndroidTest {

    @Inject
    private W3cActions w3cActions;

    @Inject
    private DriverManager driverManager;

    private AppiumDriver driver;

    @BeforeTest
    public void setUp() {
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
    }

//    @Test
    public void testMoveToElement() {
        driver.findElement(AppiumBy.androidUIAutomator("text(\"Views\")")).click();
        delay(2000);
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

    private void delay(long timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
