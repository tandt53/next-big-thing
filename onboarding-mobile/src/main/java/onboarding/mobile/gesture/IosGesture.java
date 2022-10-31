package onboarding.mobile.gesture;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import onboarding.mobile.drivermanager.DriverManager;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;
import java.util.Map;

public class IosGesture implements JsGesture {

    private static final String GESTURE_SWIPE = "mobile: swipe";
    private static final String GESTURE_SCROLL = "mobile: scroll";
    private static final String GESTURE_DRAG_AND_DROP = "mobile: dragFromToForDuration";
    private static final String GESTURE_TAP = "mobile: tap";
    private AppiumDriver driver;


    @Inject
    public IosGesture(DriverManager manager) {
        this.driver = manager.getDriver();
    }

    public void swipeLeft() {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "left");
        params.put("velocity", 2500);

        ((JavascriptExecutor) driver)
                .executeScript(GESTURE_SWIPE, params);
    }

    public void swipeRight() {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "right");
        params.put("velocity", 2500);

        ((JavascriptExecutor) driver)
                .executeScript(GESTURE_SWIPE, params);
    }

    public void swipe(String elementId, Direction direction) {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", direction.getDirection());
        params.put("velocity", 2500);
        params.put("element", elementId);

        ((JavascriptExecutor) driver).executeScript(GESTURE_SWIPE, params);
    }

    public boolean scrollUp() {
        try {
            ((JavascriptExecutor) driver).executeScript(GESTURE_SCROLL, ImmutableMap.of(
                    "direction", "up"
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean scrollDown() {
        try {
            ((JavascriptExecutor) driver).executeScript(GESTURE_SCROLL, ImmutableMap.of(
                    "direction", "down"
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void scroll(String elementId, Direction direction) {
        ((JavascriptExecutor) driver).executeScript(GESTURE_SCROLL, ImmutableMap.of(
                "direction", direction.getDirection(),
                "elementId", elementId
        ));
    }

    public void dragAndDrop(int startX, int startY, int endX, int endY) {
        ((JavascriptExecutor) driver).executeScript(GESTURE_DRAG_AND_DROP, ImmutableMap.of(
                "duration", 2,
                "fromX", startX,
                "fromY", startY,
                "toX", endX,
                "toY", endY
        ));
    }

    public void tap(int x, int y) {
        ((JavascriptExecutor) driver).executeScript(GESTURE_TAP, ImmutableMap.of(
                "x", x,
                "y", y
        ));
    }

    public void tap(String elementId) {
        ((JavascriptExecutor) driver).executeScript(GESTURE_TAP, ImmutableMap.of(
                "elementId", elementId
        ));
    }
}