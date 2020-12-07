package com.tandt53.automation.web.element;

import com.tandt53.automation.web.Conditions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class ElementImpl implements Element {

    private By locator;
    private ElementInfo elementInfo;
    private Function<By, ExpectedCondition<WebElement>> waitForElement = Conditions.PRESENCE; // default wait strategy
    private Function<By, ExpectedCondition<List<WebElement>>> waitForListElement = Conditions.PRESENCE_ALL; // default wait strategy
    private WebDriver driver;
    private WebDriverWait wait;

    ElementImpl() {
    }

    public void setElementInfo(ElementInfo elementInfo) {
        this.elementInfo = elementInfo;
    }

    public ElementInfo getElementInfo() {
        return elementInfo;
    }

    public ElementImpl(ElementInfo elementInfo, WebDriver driver) {
        this.elementInfo = elementInfo;
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10000);
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
            case ID:
                locator = By.id(this.elementInfo.getLocatorValue());
                break;

            case CSS_SELECTOR:
                locator = By.cssSelector(this.elementInfo.getLocatorValue());
                break;

            case CLASS_NAME:
                locator = By.className(this.elementInfo.getLocatorValue());
                break;

            case XPATH:
                locator = By.xpath(this.elementInfo.getLocatorValue());
                break;

            case LINK_TEXT:
                locator = By.linkText(this.elementInfo.getLocatorValue());
                break;

            case TAG_NAME:
                locator = By.tagName(this.elementInfo.getLocatorValue());
                break;

            case PARTIAL_LINK_TEXT:
                locator = By.partialLinkText(this.elementInfo.getLocatorValue());
                break;

            case NAME:
                locator = By.name(this.elementInfo.getLocatorValue());
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
        return waitUntil(this.waitForElement.apply(getLocator()), timeout).getText();
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

    private WebElement getElement() {
        return waitUntil(this.waitForElement.apply(getLocator()));
    }

    private WebElement getElement(long timeout) {
        return waitUntil(this.waitForElement.apply(getLocator()), timeout);
    }

    private <T> T waitUntil(ExpectedCondition<T> condition, long timeout) {
        return getWait(timeout).until(condition);
    }

    private <T> T waitUntil(ExpectedCondition<T> condition) {
        return wait.until(condition);
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
