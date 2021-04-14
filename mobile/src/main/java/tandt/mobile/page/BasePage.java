package tandt.mobile.page;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import javafx.util.Pair;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import tandt.common.Log;
import tandt.mobile.ElementFactory;
import tandt.mobile.element.BaseMobileElement;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

/**
 * The base class for all pages.
 */
public abstract class BasePage<TPage extends BasePage> {

    /**
     * url
     */
    public String url;

    @Inject
    protected MobileDriver mobileDriver;

    protected AppiumDriver<WebElement> driver;

    @SuppressWarnings("unchecked")
    public Log PLog = new Log(((TPage) BasePage.this).getClass());

    public BasePage() {
        ElementFactory.initElements(this);
        driver = (AppiumDriver<WebElement>) mobileDriver;
    }

    /**
     * open  page with url is not null
     */
    public TPage open() {
        if (url != null)
            return open(url);
        return null;
    }


    /**
     * open page with a specific url
     */
    @SuppressWarnings("unchecked")
    private TPage open(String url) {
        driver.get(url);
        return (TPage) this;
    }

    /**
     * close page and quit driver
     */
    public void close() {
        if (driver != null)
            driver.quit();
    }

    /**
     * getTitle
     *
     * @return title of page
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Control issue setText for App Flutter
     *
     * @param text
     */
    public void setTextElementFlutter(String text) {
        driver.getKeyboard().sendKeys(text);
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public String refreshScreen() {
        return driver.getPageSource();
    }

    public void scrollByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width / anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * endPercentage);

        new TouchAction(driver).press(point(anchor, startPoint)).waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(anchor, endPoint)).release().perform();
    }

    public void goToTop(BaseMobileElement baseMobileElement) {
        int xElementPoint = baseMobileElement.getLocation().x;
        int yElementPoint = baseMobileElement.getLocation().y;
        int yDestination = (int) (driver.manage().window().getSize().height * 0.05);


        new TouchAction(driver).press(point(xElementPoint, yElementPoint)).waitAction(waitOptions(ofMillis(2000)))
                .moveTo(point(xElementPoint, yDestination)).release().perform();
    }

    public void swipeElementUp(BaseMobileElement baseMobileElement, int length, long timeout) {
        Point elementPoint = baseMobileElement.getLocation();
        Dimension elementDimension = baseMobileElement.getSize();
        System.out.println(elementPoint.x + "; " + elementPoint.y);
        System.out.println(elementDimension.width + "; " + elementDimension.height);

        int startX = elementPoint.x + elementDimension.width / 2;
        int startY = elementPoint.y + elementDimension.height / 2;
        int endX = startX;
        int endY = startY - length;
        new TouchAction(driver).press(point(startX, startY)).waitAction(waitOptions(ofMillis(timeout)))
                .moveTo(point(endX, endY)).release().perform();
    }

    public void swipeElementDown(BaseMobileElement baseMobileElement, int length, long timeout) {
        Point elementPoint = baseMobileElement.getLocation();
        Dimension elementDimension = baseMobileElement.getSize();
        System.out.println(elementPoint.x + "; " + elementPoint.y);
        System.out.println(elementDimension.width + "; " + elementDimension.height);

        int startX = elementPoint.x + elementDimension.width / 2;
        int startY = elementPoint.y + elementDimension.height / 2;
        int endX = startX;
        int endY = startY + length;
        new TouchAction(driver).press(point(startX, startY)).waitAction(waitOptions(ofMillis(timeout)))
                .moveTo(point(endX, endY)).release().perform();
    }

    public boolean scrollDownToElement(BaseMobileElement baseMobileElement, long timeout) {
        long startTime = System.currentTimeMillis();
        long duration = 0;
        int times = 0;
        boolean isDisplayed = false;
        while (timeout > duration) {
            if (baseMobileElement.isDisplayed(10)) {
                isDisplayed = true;
                break;
            }
            scrollByPercentages(0.80, 0.40, 2);
            duration = System.currentTimeMillis() - startTime;
            times++;
            System.out.println("duration: " + duration + "Scroll: " + times);
        }
        return isDisplayed;
    }

    public boolean scrollUpToElement(BaseMobileElement baseMobileElement, long timeout) {
        long startTime = System.currentTimeMillis();
        long duration = 0;
        int times = 0;
        boolean isDisplayed = false;
        while (timeout > duration) {
            if (baseMobileElement.isDisplayed()) {
                isDisplayed = true;
                break;
            }
            scrollByPercentages(0.20, 0.80, 2);
            duration = System.currentTimeMillis() - startTime;
            times++;
            System.out.println("duration: " + duration + "Scroll: " + times);
        }
        return isDisplayed;
    }

    public boolean scrollUpToElement(BaseMobileElement baseMobileElement, long timeout, double startPercentage, double endPercentage) {
        long startTime = System.currentTimeMillis();
        long duration = 0;
        int times = 0;
        boolean isDisplayed = false;
        while (timeout > duration) {
            if (baseMobileElement.isDisplayed()) {
                isDisplayed = true;
                break;
            }
            scrollByPercentages(startPercentage, endPercentage, 2);
            duration = System.currentTimeMillis() - startTime;
            times++;
            System.out.println("duration: " + duration + "Scroll: " + times);
        }
        return isDisplayed;
    }


    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void cancelAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void sendkeyAlert(String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    public String getTextAlert() {
        return driver.switchTo().alert().getText();
    }

    public void scrollDown() {
        new TouchAction<>(driver).press(getOffset().getKey()).waitAction().moveTo(getOffset().getValue()).release()
                .perform();
    }

    public void scrollUp() {
        new TouchAction<>(driver).press(getOffset().getValue()).waitAction().moveTo(getOffset().getKey()).release()
                .perform();
    }

    private Pair<PointOption, PointOption> getOffset() {

        // The viewing size of the device
        Dimension size = driver.manage().window().getSize();

        // Starting y location set to 80% of the height (near bottom)
        int starty = (int) (size.height * 0.70);
        // Ending y location set to 20% of the height (near top)
        int endy = (int) (size.height * 0.20);
        // x position set to mid-screen horizontally
        int startx = size.width / 2;

        return new Pair<>(point(startx, starty), point(startx, endy));
    }

    public void switchContextToWebView() {
        if (!driver.getContext().contains("WEBVIEW")) {
            switchToContext("WEBVIEW");
        }
    }

    public void switchContextToNative() {
        if (!driver.getContext().contains("NATIVE_APP")) {
            switchToContext("NATIVE_APP");
        }
    }

    private void switchToContext(String contextName) {
        Set<String> contextNames = driver.getContextHandles();
        for (String context : contextNames) {
            System.out.println("context=" + context);
            if (context.contains(contextName)) {
                driver.context(context);
                break;
            }
        }

    }

    public void back() {
        driver.navigate().back();
    }

    public void swipeRight(BaseMobileElement baseMobileElement, long timeout) {
        Point elementPoint = baseMobileElement.getLocation();
        Dimension size = baseMobileElement.getSize();

        int startX = (int) (elementPoint.x + (size.width * 0.80));
        int endX = (int) ((size.width * 0.10));
        int startY = elementPoint.y + size.height / 2;

        new TouchAction<>(driver).press(point(startX, startY)).waitAction(waitOptions(ofMillis(timeout)))
                .moveTo(point(endX, startY)).release().perform();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swipeLeft(BaseMobileElement baseMobileElement, long timeout) {
        Point elementPoint = baseMobileElement.getLocation();
        Dimension size = baseMobileElement.getSize();

        int startX = (int) (elementPoint.x + (size.width * 0.05));
        int endX = (int) ((size.width * 0.90));
        int startY = elementPoint.y + size.height / 2;

        new TouchAction(driver).press(point(startX, startY)).waitAction(waitOptions(ofMillis(timeout)))
                .moveTo(point(endX, startY)).release().perform();

    }

    /**
     * @param_direction : name of action want to scroll: down, up,
     */

    public void swipeRightWithElementLocation(Point elementLocation, Dimension elementSize, long timeout) {
        int screenWidth = driver.manage().window().getSize().getWidth();

        int startX = (int) (screenWidth * 0.95);
        int endX = (int) ((screenWidth * 0.1));
        int startY = elementLocation.y + elementSize.height / 2;

        new TouchAction(driver).press(point(startX, startY)).waitAction(waitOptions(ofMillis(timeout)))
                .moveTo(point(endX, startY)).release().perform();
    }

    public void swipeLeftWithElementLocation(Point elementLocation, Dimension elementSize, long timeout) {
        int screenWidth = driver.manage().window().getSize().getWidth();

        int startX = (int) (screenWidth * 0.1);
        int endX = (int) ((screenWidth * 0.7));
        int startY = elementLocation.y + elementSize.height / 2;
        new TouchAction(driver).press(point(startX, startY)).waitAction(waitOptions(ofMillis(timeout)))
                .moveTo(point(endX, startY)).release().perform();
    }

    /**
     * @param _direction : name of action want to scroll: down, up,
     */
    public void scrollIos(String _direction) {
        driver.executeScript("mobile: scroll", ImmutableMap.of("direction", _direction));
    }

    protected void clickToPointOffElement(Point elementLocation) {
        TouchAction action = new TouchAction(driver);
        action.press(point(elementLocation.getX(), elementLocation.getY()))
                .waitAction(waitOptions(ofSeconds(1))).release().perform();
    }

    protected void longPressToElement(BaseMobileElement baseMobileElement) throws InterruptedException {
        new Actions(driver).clickAndHold(baseMobileElement.getWebElement()).perform();
        Thread.sleep(3000);

    }

    protected void touchAndHoldForiOS(WebElement element) {
        Map<String, Object> args = new HashMap<>();
        args.put("element", ((io.appium.java_client.MobileElement) element).getId());
        args.put("duration", 1.5);
        driver.executeScript("mobile: touchAndHold", args);
    }

    protected void tapToPointByPercent(AppiumDriver<WebElement> driver, int percentX, int percentY) {
        int getWidth = driver.manage().window().getSize().getWidth();
        int getHeight = driver.manage().window().getSize().getHeight();
        int x = (int) (getWidth * percentX) / 100;
        int y = (int) (getHeight * percentY) / 100;
        System.out.println("Width is " + getWidth + "   ------   " + "Height is " + getHeight);
        System.out.println("point of action is: (" + x + "," + y + ")");
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(point(x, y)).perform();
    }

    /*
     * An overloading of Scroll down to element function Arg2 - double type: Add an
     * argument of how many percent of screen height will be scrolled per time.
     */

    public boolean scrollDownToElement(BaseMobileElement baseMobileElement, double percentage, long timeout) {
        long startTime = System.currentTimeMillis();
        long duration = 0;
        int times = 0;
        boolean isDisplayed = false;
        while (timeout > duration) {
            if (baseMobileElement.isDisplayed(10)) {
                isDisplayed = true;
                break;
            }
            if (percentage >= 0.80)
                scrollByPercentages(0.90, 0.15, 2);
            else
                scrollByPercentages(0.90, 0.90 - percentage, 2);

            duration = System.currentTimeMillis() - startTime;
            times++;
            System.out.println("duration: " + duration + "Scroll: " + times);
        }
        return isDisplayed;
    }

    /*
     * iOS elements on GUI sometimes has visible=false attribute.
     *
     * Arg2 - isScrollDown: if true: it will scroll down, if false: scroll up to find element.
     *
     * Arg3 - double type: Add an argument of how many percent of screen height will
     * be scrolled per time. (0.1 -> 0.9)
     */
    public boolean scrollDownToPresentElement(BaseMobileElement baseMobileElement, boolean isScrollDown, double percentage,
                                              long timeout) {
        long startTime = System.currentTimeMillis();
        long duration = 0;
        int times = 0;
        boolean isPresent = false;
        while (timeout > duration) {
            if (baseMobileElement.isPresent(10)) {
                isPresent = true;
                break;
            }

            if (isScrollDown) {
                if (percentage >= 0.80)
                    scrollByPercentages(0.90, 0.15, 2);
                else
                    scrollByPercentages(0.90, 0.90 - percentage, 2);
            } else {
                if (percentage >= 0.80)
                    scrollByPercentages(0.15, 0.90, 2);
                else
                    scrollByPercentages(0.90 - percentage, 0.90, 2);
            }

            duration = System.currentTimeMillis() - startTime;
            times++;
            System.out.println("duration: " + duration + "Scroll: " + times);
        }
        return isPresent;
    }
}
