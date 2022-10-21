package onboarding.mobile.gesture;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import onboarding.mobile.drivermanager.DriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;

import java.util.HashMap;
import java.util.Map;

import static onboarding.mobile.Constants.*;

/**
 * reference: https://appium.io/docs/en/writing-running-appium/android/android-mobile-gestures
 *
 * @return
 */
public class AndroidGesture {

    private static final int SPEED = 1000;

    private static final String GESTURE_SWIPE = "mobile: swipeGesture";
    private static final String GESTURE_SCROLL = "mobile: scrollGesture";
    private static final String GESTURE_DRAG_AND_DROP = "mobile: dragGesture";
    private static final String GESTURE_TAP = "mobile: clickGesture";

    private AppiumDriver driver;

    @Inject
    public AndroidGesture(DriverManager manager) {
        this.driver = manager.getDriver();
    }

    public void swipeLeft() {
        Dimension size = driver.manage().window().getSize();
        Point p = driver.manage().window().getPosition();
        int left = (int) (p.x + size.width * PERCENT_SCREEN_MIDDLE);
        int top = (int) (p.y + size.height * PERCENT_SCREEN_MIDDLE);
        int width = (int) (size.width * PERCENT_SCREEN_MAX);
        int height = (int) (size.height * PERCENT_SCREEN_MAX);

        swipe(Direction.LEFT, top, left, width, height);
    }

    public void swipeRight() {
        Dimension size = driver.manage().window().getSize();
        Point p = driver.manage().window().getPosition();
        int left = (int) (p.x + size.width * PERCENT_SCREEN_MIDDLE);
        int top = (int) (p.y + size.height * PERCENT_SCREEN_MIDDLE);
        int width = (int) (size.width * PERCENT_SCREEN_MAX);
        int height = (int) (size.height * PERCENT_SCREEN_MAX);

        swipe(Direction.RIGHT, top, left, width, height);
    }

    public void swipe(Direction direction, int top, int left, int width, int height) {
        Map<String, Object> params = new HashMap<>();
        params.put("left", left);
        params.put("top", top);
        params.put("width", width);
        params.put("height", height);
        params.put("direction", direction.getDirection());
        params.put("percent", 1.0);
        params.put("speed", SPEED);

        ((JavascriptExecutor) driver)
                .executeScript(GESTURE_SWIPE, params);
    }

    public void swipe(String elementId, Direction direction) {
        Map<String, Object> params = new HashMap<>();
        params.put("element", elementId);
        params.put("direction", direction.getDirection());
        params.put("percent", 1.0);
        params.put("speed", SPEED);

        ((JavascriptExecutor) driver)
                .executeScript(GESTURE_SWIPE, params);
    }

    public boolean scrollDown() {
        Dimension size = driver.manage().window().getSize();
        Point p = driver.manage().window().getPosition();
        int left = (int) (p.x + size.width * PERCENT_SCREEN_MIN);
        int top = (int) (p.y + size.height * PERCENT_SCREEN_MIN);
        int width = (int) (size.width * PERCENT_SCREEN_MAX);
        int height = (int) (size.height * PERCENT_SCREEN_MAX);

        return scroll(Direction.DOWN, top, left, width, height);
    }

    public boolean scrollUp() {
        Dimension size = driver.manage().window().getSize();
        Point p = driver.manage().window().getPosition();
        int left = (int) (p.x + size.width * PERCENT_SCREEN_MIN);
        int top = (int) (p.y + size.height * PERCENT_SCREEN_MIN);
        int width = (int) (size.width * PERCENT_SCREEN_MAX);
        int height = (int) (size.height * PERCENT_SCREEN_MAX);

        return scroll(Direction.UP, top, left, width, height);
    }

    public boolean scroll(Direction direction, int top, int left, int width, int height) {
        Map<String, Object> params = new HashMap<>();
        params.put("left", left);
        params.put("top", top);
        params.put("width", width);
        params.put("height", height);
        params.put("direction", direction.getDirection());
        params.put("percent", 1.0);
        params.put("speed", SPEED);

        return (Boolean) ((JavascriptExecutor) driver)
                .executeScript(GESTURE_SCROLL, params);
    }

    public void dragAndDrop(int startX, int startY, int endX, int endY) {
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "startX", startX,
                "startY", startY,
                "endX", endX,
                "endY", endY,
                "speed", SPEED
        ));
    }

    public void dragAndDrop(String elementId, int endX, int endY) {
        ((JavascriptExecutor) driver).executeScript(GESTURE_DRAG_AND_DROP, ImmutableMap.of(
                "elementId", elementId,
                "endX", endX,
                "endY", endY,
                "speed", SPEED
        ));
    }

    public void tap(int x, int y){
        ((JavascriptExecutor) driver).executeScript(GESTURE_TAP, ImmutableMap.of(
                "x", x,
                "y",y
        ));
    }

    public void tap(String elementId){
        ((JavascriptExecutor) driver).executeScript(GESTURE_TAP, ImmutableMap.of(
                "elementId", elementId
        ));
    }

}
