package com.tandt53.automation.mobile.element;

import com.tandt53.automation.mobile.Conditions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MobileElementImpl implements MobileElement {

    private By locator;
    private ElementInfo elementInfo;
    private final int _TIMEOUT = 10;
    private Function<By, ExpectedCondition<WebElement>> waitForElement = Conditions.PRESENCE; //  wait strategy
    private Function<By, ExpectedCondition<List<WebElement>>> waitForListElement = Conditions.PRESENCE_ALL; //  wait strategy
    private AppiumDriver<WebElement> driver;
    private WebDriverWait wait;

    MobileElementImpl() {
    }

    public void setElementInfo(ElementInfo elementInfo) {
        this.elementInfo = elementInfo;
    }

    public ElementInfo getElementInfo() {
        return elementInfo;
    }

    public MobileElementImpl(ElementInfo elementInfo, AppiumDriver<WebElement> driver) {
        this.elementInfo = elementInfo;
        this.driver = driver;
        this.wait = new WebDriverWait(driver, _TIMEOUT);
        initLocator();
        initWaitStrategy();
    }

    private void initWaitStrategy() {
        WaitStrategy waitStrategy = this.elementInfo.getStrategy();
        if (waitStrategy != null)

            switch (waitStrategy) {
                case VISIBILITY:
                    this.waitForElement = Conditions.VISIBILITY;
                    this.waitForListElement = Conditions.VISIBILITY_ALL;
                    break;

                case PRESENCE:
                    this.waitForElement = Conditions.PRESENCE;
                    this.waitForListElement = Conditions.PRESENCE_ALL;
                    break;
            }
    }

    private void initLocator() {
        switch (this.elementInfo.getLocatorType()) {
            case CLASS_NAME:
                locator = MobileBy.className(this.elementInfo.getLocatorValue());
                break;

            case ID:
                locator = MobileBy.id(this.elementInfo.getLocatorValue());
                break;

            case NAME:
                locator = MobileBy.name(this.elementInfo.getLocatorValue());
                break;

            case XPATH:
                locator = MobileBy.xpath(this.elementInfo.getLocatorValue());
                break;

            case ACCESSIBILITY_ID:
                locator = MobileBy.AccessibilityId(this.elementInfo.getLocatorValue());
                break;

            case IOS_PREDICATE:
                locator = MobileBy.iOSNsPredicateString(this.elementInfo.getLocatorValue());
                break;

            case IOS_CLASS_CHAIN:
                locator = MobileBy.iOSClassChain(this.elementInfo.getLocatorValue());
                break;

            case ANDROID_UIAUTOMATOR:
                locator = MobileBy.AndroidUIAutomator(this.elementInfo.getLocatorValue());
                break;

            case ANDROID_DATA_MATCHER:
                locator = MobileBy.androidDataMatcher(this.elementInfo.getLocatorValue());
                break;

            case ANDROID_VIEW_MATCHER:
                locator = MobileBy.androidViewMatcher(this.elementInfo.getLocatorValue());
                break;
            case ANDROID_VIEW_TAG:
                locator = MobileBy.AndroidViewTag(this.elementInfo.getLocatorValue());
                break;
        }
    }

    private FluentWait<WebDriver> getWait(long timeout) {
        return this.wait.withTimeout(Duration.ofSeconds(timeout));
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
    public MobileElement formatLocatorValue(String... eventName) {
        this.elementInfo.setLocatorValue(String.format(this.elementInfo.getLocatorValue(), eventName));
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
    public WebElement getWebElement() {
        return null;
    }

    @Override
    public boolean isPresent(int i) {
        return false;
    }

    private WebElement getElement() {
        return waitUntil(this.waitForElement.apply(getLocator()));
    }

    private WebElement getElement(long timeout) {
        return waitUntil(this.waitForElement.apply(getLocator()), timeout);
    }

    private <T> T waitUntil(ExpectedCondition<T> condition, long timeout) {
        return this.wait.withTimeout(Duration.ofSeconds(timeout)).until(condition);
    }

    private <T> T waitUntil(ExpectedCondition<T> condition) {
        return this.wait.until(condition);
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
