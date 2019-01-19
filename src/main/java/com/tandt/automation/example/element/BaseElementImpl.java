package com.tandt.automation.example.element;

import com.tandt.automation.example.Conditions;
import com.tandt.automation.example.WaitStrategy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class BaseElementImpl extends By implements WaitStrategy, BaseElement {

    private By locator;
    private long timeout = 5;

    private String name;
    private String locatorValue;
    private LocatorType locatorType;

    public BaseElementImpl(final LocatorType locatorType, final String locatorValue, final String name) {
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
        this.name = name;

        if (getLocatorType() != null && getLocatorValue() != null) {
            initElement(getLocatorType(), getLocatorValue());
        }
    }

    private void initElement(LocatorType locatorType, String locatorValue) {
        // TODO Auto-generated method stub
        switch (locatorType) {
            case ID:
                locator = By.id(locatorValue);
//                locator = By.xpath("//*[contains(@resource-id,'" + locatorValue + "']");
                break;

            case CSS_SELECTOR:
                locator = By.cssSelector(locatorValue);
                break;

            case CLASS_NAME:
                locator = By.className(locatorValue); // for web
//                locator = By.xpath("//" + locatorValue); // for mobile
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

    @Override
    public WebElement waitUntil(final Function<By, ExpectedCondition<WebElement>> condition) {
        try {
            return waitUntil(condition, timeout);
        } catch (NoSuchElementException | TimeoutException e1) {
            System.out.println("Unable to find the element: " + name);
            e1.printStackTrace();
        }
        return null;
    }

    private WebElement waitUntil(final Function<By, ExpectedCondition<WebElement>> condition, long timeout) {
        return getWait(timeout).until(condition.apply(getLocator()));
    }

    //	@Override
    public List<WebElement> waitUntilAll(final Function<By, ExpectedCondition<List<WebElement>>> condition) {
        try {
            return waitUntilAll(condition, timeout);
        } catch (NoSuchElementException | TimeoutException e1) {
            System.out.println("Unable to find the element: " + name);
            e1.printStackTrace();
        }
        return null;
    }

    private List<WebElement> waitUntilAll(final Function<By, ExpectedCondition<List<WebElement>>> condition, long timeout) {
        return getWait(timeout).until(condition.apply(getLocator()));
    }

    @Override
    public String getText() throws TimeoutException {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getText();
    }

    @Override
    public void setText(String text) throws TimeoutException {
        waitUntil(Conditions.CONDITION_VISIBILITY).sendKeys(text);
//		waitUntil(Conditions.CONDITION_VISIBILITY).sendKeys(text);

    }

    @Override
    public boolean isDisplayed() throws TimeoutException {
        try {
            return waitUntil(Conditions.CONDITION_VISIBILITY).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void submit() {
        waitUntil(Conditions.CONDITION_VISIBILITY).submit();
    }

    @Override
    public void click() throws TimeoutException {
        waitUntil(Conditions.CONDITION_VISIBILITY).click();
    }

    @Override
    public String getText(BaseElement fullname) throws TimeoutException {
        fullname.waitUntil(Conditions.CONDITION_VISIBILITY);
        return getText();
    }

    public void clearText() throws TimeoutException {
        waitUntil(Conditions.CONDITION_VISIBILITY).clear();
    }

    @Override
    public List<WebElement> findElements(final SearchContext searchContext) {
        return waitUntilAll(Conditions.CONDITION_PRESENCE_OF_ALL_ELEMENT);
    }

    public By getLocator() {
        return locator;
    }

    public String getLocatorValue() {
        return locatorValue;
    }

    public void setLocatorValue(String locatorValue) {
        this.locatorValue = locatorValue;
    }

    public LocatorType getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(LocatorType locatorType) {
        this.locatorType = locatorType;
    }

    public String getAttributeValue(String attribute) {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getAttribute(attribute);
    }

    public String getTagName() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getTagName();
    }

    public boolean isEnabled() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).isEnabled();
    }

    public boolean isSelected() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).isSelected();
    }

    public Point getLocation() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getLocation();
    }

    public Dimension getSize() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getSize();
    }

    public Rectangle getRect() {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getRect();
    }

    public String getCssValue(String propertyName) {
        return waitUntil(Conditions.CONDITION_VISIBILITY).getCssValue(propertyName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
