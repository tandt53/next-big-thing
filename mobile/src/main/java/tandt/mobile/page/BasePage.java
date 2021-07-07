package tandt.mobile.page;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import tandt.common.Log;
import tandt.mobile.ElementFactory;
import tandt.mobile.drivermanager.DriverManager;
import ui.element.Element;

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

    protected AppiumDriver<WebElement> driver;

    @SuppressWarnings("unchecked")
    public Log PLog = new Log(((TPage) BasePage.this).getClass());

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
        new TouchAction<>(driver).press(getOffset().get("start")).waitAction().moveTo(getOffset().get("end")).release()
                .perform();
    }

    public void scrollUp() {
        new TouchAction<>(driver).press(getOffset().get("end")).waitAction().moveTo(getOffset().get("start")).release()
                .perform();
    }

    private Map<String, PointOption> getOffset() {

        // The viewing size of the device
        Dimension size = driver.manage().window().getSize();

        // Starting y location set to 80% of the height (near bottom)
        int starty = (int) (size.height * 0.70);
        // Ending y location set to 20% of the height (near top)
        int endy = (int) (size.height * 0.20);
        // x position set to mid-screen horizontally
        int startx = size.width / 2;

        Map<String, PointOption> map = new HashMap<>();
        map.put("start", point(startx, starty));
        map.put("end", point(startx, endy));
        return map;
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


    @Inject
    private void setManager(DriverManager driverManager) {
        driver = driverManager.getDriver();
        ElementFactory.initElements(driver, this);

    }
}
