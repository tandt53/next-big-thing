package com.tandt53.automation.web.element;

import com.tandt53.automation.web.Conditions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Function;

public class ElementImpl implements Element {

    private By locator;
    private ElementInfo elementInfo;
    private Function<By, ExpectedCondition<WebElement>> waitForElement = Conditions.PRESENCE; //  wait strategy
    private Function<By, ExpectedCondition<List<WebElement>>> waitForListElement = Conditions.PRESENCE_ALL; //  wait strategy
    private WebDriver driver;
    private long timeout = 5;

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
        initLocator(this.elementInfo.getLocatorType(), this.elementInfo.getLocatorValue());
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

    private void initLocator(LocatorType locatorType, String locatorValue) {
        switch (locatorType) {
            case ID:
                locator = By.id(locatorValue);
                break;

            case CSS_SELECTOR:
                locator = By.cssSelector(locatorValue);
                break;

            case CLASS_NAME:
                locator = By.className(locatorValue);
                break;

            case XPATH:
                locator = By.xpath(locatorValue);
                break;

            case LINK_TEXT:
                locator = By.linkText(locatorValue);
                break;

            case TAG_NAME:
                locator = By.tagName(locatorValue);
                break;

            case PARTIAL_LINK_TEXT:
                locator = By.partialLinkText(locatorValue);
                break;

            case NAME:
                locator = By.name(locatorValue);
                break;

        }
    }

    public WebElement waitUntil(final Function<By, ExpectedCondition<WebElement>> condition) throws java.util.NoSuchElementException, TimeoutException {
        return waitUntil(condition, timeout);
    }


    public WebElement getWebElement() {
        return waitUntil(waitForElement);
    }

    @Override
    public List<WebElement> getElements() {
        return waitUntilAll(waitForListElement);
    }

    @Override
    public List<WebElement> getElements(long timeout) {
        return waitUntilAll(waitForListElement, timeout);
    }

    private <V> V waitUntil(final Function<By, ExpectedCondition<V>> condition, long timeout) throws NoSuchElementException, TimeoutException {
        return getWait(timeout).until(condition.apply(getLocator()));
    }

    public List<WebElement> waitUntilAll(final Function<By, ExpectedCondition<List<WebElement>>> condition) throws NoSuchElementException, TimeoutException {
        return waitUntilAll(condition, timeout);
    }

    private List<WebElement> waitUntilAll(final Function<By, ExpectedCondition<List<WebElement>>> condition, long timeout) throws NoSuchElementException, TimeoutException {
        return getWait(timeout).until(condition.apply(getLocator()));
    }

    private WebDriverWait getWait(long timeout) {
        return new WebDriverWait(driver, timeout);
    }


    @Override
    public void setText(CharSequence... text) {
        waitUntil(waitForElement).sendKeys(text);
    }

    @Override
    public String getText() {
        return waitUntil(waitForElement).getText();
    }

    @Override
    public String getText(long timeout) {
        return waitUntil(waitForElement, timeout).getText();
    }

    @Override
    public boolean isDisplayed() {
        return waitUntil(waitForElement).isDisplayed();
    }

    @Override
    public boolean isDisplayed(long timeout) {
        return waitUntil(waitForElement, timeout).isDisplayed();
    }


    @Override
    public void click() throws TimeoutException {
        waitUntil(waitForElement).click();
    }

    @Override
    public void click(long timeout) {
        waitUntil(waitForElement, timeout).click();
    }


    public void clearText() {
        waitUntil(waitForElement).clear();
    }

    @Override
    public void clearText(long timeout) {
        waitUntil(waitForElement, timeout).clear();
    }

    public void submit() {
        waitUntil(waitForElement).submit();
    }

    public By getLocator() {
        return locator;
    }


    @Override
    public String getAttributeValue(String attribute) {
        return waitUntil(waitForElement).getAttribute(attribute);
    }

    public String getTagName() {
        return waitUntil(waitForElement).getTagName();
    }

    public boolean isEnabled() {
        return waitUntil(waitForElement).isEnabled();
    }


    @Override
    public boolean isSelected() {
        return waitUntil(waitForElement).isSelected();
    }

    @Override
    public Point getLocation() {
        return waitUntil(waitForElement).getLocation();
    }

    @Override
    public Dimension getSize() {
        return waitUntil(waitForElement).getSize();
    }

    @Override
    public Rectangle getRect() {
        return waitUntil(waitForElement).getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return waitUntil(waitForElement).getCssValue(propertyName);
    }

    @Override
    public Element formatLocatorValue(String... eventName) {
        String newLocator = String.format(this.elementInfo.getLocatorValue(), eventName);
        initLocator(this.elementInfo.getLocatorType(), newLocator);
        return this;
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
