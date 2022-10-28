package onboarding.mobile.element;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import onboarding.mobile.Conditions;
import onboarding.ui.element.Element;
import onboarding.ui.element.WaitStrategy;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static org.openqa.selenium.By.*;

public class BaseMobileElementImpl implements Element {

    private By locator;
    private MobileElementInfo mobileElementInfo;
    private static final int TIMEOUT = 10;
    private Function<By, ExpectedCondition<WebElement>> waitForElement = Conditions.PRESENCE; //  wait strategy
    private Function<By, ExpectedCondition<List<WebElement>>> waitForListElement = Conditions.PRESENCE_ALL; //  wait strategy
    private WebDriverWait wait;
    private AppiumDriver driver;

    public void setElementInfo(MobileElementInfo mobileElementInfo) {
        this.mobileElementInfo = mobileElementInfo;
    }

    public MobileElementInfo getElementInfo() {
        return mobileElementInfo;
    }

    public BaseMobileElementImpl(AppiumDriver driver, MobileElementInfo mobileElementInfo) {
        this.mobileElementInfo = mobileElementInfo;
        this.driver = driver;
        initLocator();
        initWaitStrategy();
    }

    private void initWaitStrategy() {
        WaitStrategy waitStrategy = this.mobileElementInfo.getStrategy();
        if (waitStrategy != null)

            switch (waitStrategy) {
                case PRESENCE:
                    this.waitForElement = Conditions.PRESENCE;
                    this.waitForListElement = Conditions.PRESENCE_ALL;
                    break;

                case VISIBILITY:
                default:
                    this.waitForElement = Conditions.VISIBILITY;
                    this.waitForListElement = Conditions.VISIBILITY_ALL;
                    break;
            }
    }

    private void initLocator() {
        switch (this.mobileElementInfo.getType()) {
            case CLASS_NAME:
                locator = className(this.mobileElementInfo.getValue());
                break;

            case NAME:
                locator = name(this.mobileElementInfo.getValue());
                break;

            case XPATH:
                locator = xpath(this.mobileElementInfo.getValue());
                break;

            case ACCESSIBILITY_ID:
                locator = AppiumBy.accessibilityId(this.mobileElementInfo.getValue());
                break;

            case IOS_PREDICATE:
                locator = AppiumBy.iOSNsPredicateString(this.mobileElementInfo.getValue());
                break;

            case IOS_CLASS_CHAIN:
                locator = AppiumBy.iOSClassChain(this.mobileElementInfo.getValue());
                break;

            case ANDROID_UIAUTOMATOR:
                locator = AppiumBy.androidUIAutomator(this.mobileElementInfo.getValue());
                break;

            case ANDROID_DATA_MATCHER:
                locator = AppiumBy.androidDataMatcher(this.mobileElementInfo.getValue());
                break;

            case ANDROID_VIEW_MATCHER:
                locator = AppiumBy.androidViewMatcher(this.mobileElementInfo.getValue());
                break;
            case ANDROID_VIEW_TAG:
                locator = AppiumBy.androidViewTag(this.mobileElementInfo.getValue());
                break;

            case ID:
            default:
                locator = id(this.mobileElementInfo.getValue());
                break;
        }
    }

    private WebDriverWait getWait(long timeout) {
        wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
        return wait;
    }

    public By getLocator() {
        return locator;
    }

    @Override
    public void setText(CharSequence... text) {
        getElement().sendKeys(text);
    }

    @Override
    public String getText() {
        return getElement().getText();
    }

    @Override
    public String getText(long timeout) {
        return getElement(timeout).getText();
    }

    @Override
    public void click() {
        getElement().click();
    }

    @Override
    public void click(long timeout) {
        getElement(timeout).click();
    }

    @Override
    public void clearText() {
        getElement().clear();
    }

    @Override
    public void clearText(long timeout) {
        getElement(timeout).clear();
    }

    @Override
    public boolean isDisplayed() {
        return getElement().isDisplayed();
    }

    @Override
    public boolean isDisplayed(long timeout) {
        return getElement(timeout).isDisplayed();
    }

    @Override
    public void submit() {
        getElement().submit();
    }

    @Override
    public String getAttributeValue(String attribute) {
        return getElement().getAttribute(attribute);
    }

    @Override
    public String getTagName() {
        return getElement().getTagName();
    }

    @Override
    public boolean isEnabled() {
        return getElement().isEnabled();
    }

    @Override
    public boolean isSelected() {
        return getElement().isSelected();
    }

    @Override
    public Point getLocation() {
        return getElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return getElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        return getElement().getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return getElement().getCssValue(propertyName);
    }

    @Override
    public Element formatLocatorValue(String... eventName) {
        this.mobileElementInfo.setValue(String.format(this.mobileElementInfo.getValue(), eventName));
        initLocator();
        return this;
    }

    @Override
    public List<WebElement> getElements() {
        return waitUntil(this.waitForListElement.apply(getLocator()));

    }

    @Override
    public List<WebElement> getElements(long timeout) {
        return waitUntil(this.waitForListElement.apply(getLocator()), timeout);
    }

    @Override
    public boolean isPresent(int timeout) {
        return waitUntil(this.waitForElement.apply(getLocator())).isEnabled();
    }

    @Override
    public WebElement getElement() {
        return waitUntil(this.waitForElement.apply(getLocator()));
    }

    @Override
    public String getId() {
        return ((RemoteWebElement) getElement()).getId();
    }

    private WebElement getElement(long timeout) {
        return waitUntil(this.waitForElement.apply(getLocator()), timeout);
    }

    private <T> T waitUntil(ExpectedCondition<T> condition, long timeout) {
        return this.wait.withTimeout(Duration.ofSeconds(timeout)).until(condition);
    }

    private <T> T waitUntil(ExpectedCondition<T> condition) {
        return this.getWait(TIMEOUT).until(condition);
    }

    private class WaitFor {

        private ExpectedCondition<Boolean> state;
        private ExpectedCondition<WebElement> element;
        private ExpectedCondition<List<WebElement>> listElements;

        public ExpectedCondition<WebElement> visibility() {
            return ExpectedConditions.visibilityOfElementLocated(getLocator());
        }

        public ExpectedCondition<WebElement> clickable() {
            return ExpectedConditions.elementToBeClickable(getLocator());
        }

        public ExpectedCondition<WebElement> presence() {
            return ExpectedConditions.presenceOfElementLocated(getLocator());
        }

    }

}
