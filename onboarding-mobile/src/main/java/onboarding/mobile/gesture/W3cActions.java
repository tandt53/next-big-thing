package onboarding.mobile.gesture;

import com.google.inject.Inject;
import onboarding.mobile.drivermanager.DriverManager;
import onboarding.ui.element.Element;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

import static onboarding.mobile.Constants.PERCENT_SCREEN_MAX;
import static onboarding.mobile.Constants.PERCENT_SCREEN_MIN;

public class W3cActions {

    private Duration STEP_DURATION = Duration.ofMillis(2000);
    private Duration ZERO_SECOND = Duration.ofMillis(0);
    private Duration TWO_SECONDS = Duration.ofMillis(2000);
    private PointerInput.Origin VIEW = PointerInput.Origin.viewport();

    @Inject
    private DriverManager driverManager;

    /**
     * Scroll on whole screen
     * Default scroll from 80% to 20% of screen height
     *
     * @param direction - scroll direction ${@link Direction}
     */
    public void scroll(Direction direction) {
        Dimension di = driverManager.getDriver().manage().window().getSize();
        // Getting start and end points

        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;

        switch (direction) {
            case UP:
                startX = di.width / 2;
                startY = (int) (di.height * PERCENT_SCREEN_MIN);
                endX = startX;
                endY = (int) (di.height * PERCENT_SCREEN_MAX);
                break;
            case DOWN:
                startX = di.width / 2;
                startY = (int) (di.height * PERCENT_SCREEN_MAX);
                endX = startX;
                endY = (int) (di.height * PERCENT_SCREEN_MIN);
                break;
            case LEFT:
                startX = (int) (di.width * PERCENT_SCREEN_MIN);
                startY = di.height / 2;
                endX = (int) (di.width * PERCENT_SCREEN_MAX);
                endY = startY;
                break;
            case RIGHT:
                startX = (int) (di.width * PERCENT_SCREEN_MAX);
                startY = di.height / 2;
                endX = (int) (di.width * PERCENT_SCREEN_MIN);
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
        Rectangle rect = webElement.getRect();
        int startX, startY, endX, endY;

        switch (direction) {
            case LEFT:
                startX = (int) (rect.x + rect.width * PERCENT_SCREEN_MAX);
                startY = (rect.y + rect.height / 2);
                endX = (int) (rect.x + rect.width * PERCENT_SCREEN_MIN);
                endY = startY;
                break;
            case RIGHT:
                startX = (int) (rect.x + rect.width * PERCENT_SCREEN_MIN);
                startY = (rect.y + rect.height / 2);
                endX = (int) (rect.x + rect.width * PERCENT_SCREEN_MAX);
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

        driverManager.getDriver().perform(Arrays.asList(sequence));
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
        sequence.addAction(new Pause(finger1, Duration.ofMillis(100)));
        sequence.addAction(finger1.createPointerMove(STEP_DURATION, VIEW, endX, endY));
        sequence.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driverManager.getDriver().perform(Arrays.asList(sequence));
    }

    /**
     * Drag and drop element to another location (x,y)
     *
     * @param element
     * @param endX
     * @param endY
     */
    public void dragAndDrop(Element element, int endX, int endY) {
        Rectangle webElement = element.getElement().getRect();
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
        Rectangle webElement = element.getElement().getRect();
        Rectangle webElement2 = element2.getElement().getRect();
        int startX = getCenterX(webElement);
        int startY = getCenterY(webElement);
        int endX = getCenterX(webElement2);
        int endY = getCenterY(webElement2);
        dragAndDrop(startX, startY, endX, endY);
    }

    private int getCenterX(Rectangle rect) {
        return rect.x + rect.width / 2;
    }

    private int getCenterY(Rectangle rect) {
        return rect.y + rect.height / 2;
    }

}
