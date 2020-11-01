package com.tandt53.automation.web.element;

import com.tandt53.automation.web.Conditions;
import com.tandt53.automation.web.element.model.ElementInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class ElementImpl implements Element {

    private By locator;
    private ElementInfo elementInfo;
    private WebDriverWait wait;

    public void setElementInfo(ElementInfo elementInfo) {
        this.elementInfo = elementInfo;
    }

    public ElementInfo getElementInfo() {
        return elementInfo;
    }

    public ElementImpl(ElementInfo elementInfo, WebDriverWait wait) {
        this.elementInfo = elementInfo;
        this.wait = wait;
        initElement();
    }

    private void initElement() {
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

    @Override
    public List<WebElement> findElements(final SearchContext searchContext) {
        return waitUntilAll(Conditions.CONDITION_PRESENCE_OF_ALL_ELEMENT);
    }

    public List<WebElement> waitUntilAll(final Function<By, ExpectedCondition<List<WebElement>>> condition) {
        try {
            return waitUntilAll(condition);
        } catch (NoSuchElementException | TimeoutException e1) {
            System.out.println("Unable to find the element: " + this.elementInfo.getName());
            e1.printStackTrace();
        }
        return null;
    }

    private List<WebElement> waitUntilAll(final Function<By, ExpectedCondition<List<WebElement>>> condition, long timeout) {
        return getWait(timeout).until(condition.apply(getLocator()));
    }

    public By getLocator() {
        return locator;
    }

    @Override
    public void setText(CharSequence... text) {
        waitUntil(Conditions.CONDITION_VISIBILITY).sendKeys(text);
    }

    @Override
    public String getText() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getText();
    }

    @Override
    public String getText(long timeout) {
        return waitUntil(Conditions.CONDITION_VISIBILITY, timeout).getText();
    }

    @Override
    public String getText(Element element) {
        waitUntil(Conditions.CONDITION_VISIBILITY);
        return getText();
    }

    @Override
    public void click() {
        waitUntil(Conditions.CONDITION_VISIBILITY).click();
    }

    @Override
    public void click(long timeout) {
        waitUntil(Conditions.CONDITION_VISIBILITY, timeout).click();
    }

    @Override
    public void clearText() {
        waitUntil(Conditions.CONDITION_VISIBILITY).clear();
    }

    @Override
    public void clearText(long timeout) {
        waitUntil(Conditions.CONDITION_VISIBILITY, timeout).clear();
    }

    @Override
    public boolean isDisplayed() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).isDisplayed();
    }

    @Override
    public boolean isDisplayed(long timeout) {
        return waitUntil(Conditions.CONDITION_VISIBILITY, timeout).isDisplayed();
    }

    @Override
    public void submit() {
        waitUntil(Conditions.CONDITION_VISIBILITY).submit();
    }

    @Override
    public String getAttributeValue(String attribute) {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getAttribute(attribute);
    }

    @Override
    public String getTagName() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getTagName();
    }

    @Override
    public boolean isEnabled() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).isEnabled();
    }

    @Override
    public boolean isSelected() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).isSelected();
    }

    @Override
    public Point getLocation() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getLocation();
    }

    @Override
    public Dimension getSize() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getSize();
    }

    @Override
    public Rectangle getRect() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getCssValue(propertyName);
    }

    @Override
    public Element getElement(String... eventName) {
        this.elementInfo.setLocatorValue(String.format(this.elementInfo.getLocatorValue(), eventName));
        initElement();
        return this;
    }

    private <T> T waitUntil(final Function<By, ExpectedCondition<T>> condition, long timeout) {
        return getWait(timeout).until(condition.apply(getLocator()));
    }

    public <T> T waitUntil(final Function<By, ExpectedCondition<T>> condition) {
        return wait.until(condition.apply(getLocator()));
    }
}
