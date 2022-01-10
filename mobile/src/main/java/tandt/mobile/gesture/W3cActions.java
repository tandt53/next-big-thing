package tandt.mobile.gesture;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import tandt.mobile.drivermanager.DriverManager;
import ui.element.Element;

import java.time.Duration;
import java.util.Arrays;

public class W3cActions {

    private AppiumDriver driver;
    private Duration STEP_DURATION = Duration.ofMillis(500);
    private Duration ZERO_SECOND = Duration.ofMillis(0);
    private Duration TWO_SECONDS = Duration.ofMillis(100);
    private PointerInput.Origin VIEW = PointerInput.Origin.viewport();

    @Inject
    public W3cActions(DriverManager driverManager) {
        this.driver = driverManager.getDriver();
    }

    /**
     * Scroll on whole screen
     * Default scroll from 85% to 15% of screen height
     *
     * @param direction - scroll direction ${@link Direction}
     */
    public void scroll(Direction direction) {
        Dimension di = driver.manage().window().getSize(); // with pixel 4XL: 1440, 3080
        // Getting start and end points

        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;

        switch (direction) {
            case UP:
                startX = di.width / 2;
                startY = (int) (di.height * 0.15);
                endX = startX;
                endY = (int) (di.height * 0.85);
                break;
            case DOWN:
                startX = di.width / 2;
                startY = (int) (di.height * 0.85);
                endX = startX;
                endY = (int) (di.height * 0.15);
                break;
            case LEFT:
                startX = (int) (di.width * 0.15);
                startY = di.height / 2;
                endX = (int) (di.width * 0.85);
                endY = startY;
                break;
            case RIGHT:
                startX = (int) (di.width * 0.85);
                startY = di.height / 2;
                endX = (int) (di.width * 0.15);
                endY = startY;
                break;
        }
        scroll(startX, startY, endX, endY);
    }

    /**
     * Swipe left or right inside element
     * @param element
     * @param direction
     */
    public void swipe(Element element, Direction direction) {
        WebElement webElement = element.getElement();
        Dimension di = webElement.getSize();
        Rectangle rect = webElement.getRect();
        int startX, startY, endX, endY;

        switch (direction) {
            case LEFT:
                startX = (int) (rect.getX() + di.getWidth() * 0.85);
                startY = (rect.getY() + di.getHeight() / 2);
                endX = (int) (rect.getX() + di.getWidth() * 0.15);
                endY = startY;
                break;
            case RIGHT:
                startX = (int) (rect.getX() + di.getWidth() * 0.15);
                startY = (rect.getY() + di.getHeight() / 2);
                endX = (int) (rect.getX() + di.getWidth() * 0.85);
                endY = startY;
                break;
            default:
                return;
        }
        scroll(startX, startY, endX, endY);
    }


    /**
     * Scroll from startX, startY to endX, endY
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    public void scroll(int startX, int startY, int endX, int endY) {
        if (startX == endX && startY == endY) {
            return;
        }
        // Build a sequence of actions
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 0);
        sequence.addAction(finger1.createPointerMove(ZERO_SECOND, VIEW, startX, startY));
        sequence.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(new Pause(finger1, Duration.ofMillis(10)));
        sequence.addAction(finger1.createPointerMove(STEP_DURATION, VIEW, endX, endY));
        sequence.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(sequence));
    }

    /**
     * Drag and drop from source location (startX, startY) to destination location (endX, endY)
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    public void dragAndDrop(int startX, int startY, int endX, int endY) {
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 0);
        sequence.addAction(finger1.createPointerMove(TWO_SECONDS, VIEW, startX, startY));
        sequence.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(new Pause(finger1, Duration.ofMillis(10)));
        sequence.addAction(finger1.createPointerMove(STEP_DURATION, VIEW, endX, endY));
        sequence.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(sequence));
    }

    /**
     * Drag and drop element to another location (x,y)
     *
     * @param element
     * @param endX
     * @param endY
     */
    public void dragAndDrop(Element element, int endX, int endY) {
        WebElement webElement = element.getElement();
        int startX = getCenterX(webElement);
        int startY = getCenterY(webElement);
        dragAndDrop(startX, startY, endX, endY);
    }

    /**
     * Drag and drop element to another element
     *
     * @param element
     * @param element2
     */
    public void dragAndDrop(Element element, Element element2) {
        WebElement webElement = element.getElement();
        WebElement webElement2 = element2.getElement();
        int startX = getCenterX(webElement);
        int startY = getCenterY(webElement);
        int endX = getCenterX(webElement2);
        int endY = getCenterY(webElement2);
        dragAndDrop(startX, startY, endX, endY);
    }

    private int getCenterX(WebElement element) {
        return element.getLocation().getX() + element.getRect().getWidth() / 2;
    }

    private int getCenterY(WebElement element) {
        return element.getLocation().getY() + element.getRect().getHeight() / 2;
    }

}
