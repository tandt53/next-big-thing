package tandt.web.element;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tandt.web.Conditions;
import ui.element.Element;
import ui.element.WaitStrategy;

import java.util.List;
import java.util.function.Function;

public class BaseWebElementImpl implements Element {

    private WebDriver driver;
    private By locator;
    private WebElementInfo webElementInfo;
    private Function<By, ExpectedCondition<WebElement>> waitForElement = Conditions.PRESENCE; //  wait strategy
    private Function<By, ExpectedCondition<List<org.openqa.selenium.WebElement>>> waitForListElement = Conditions.PRESENCE_ALL; //  wait strategy
    private long timeout = 5;

    public void setElementInfo(WebElementInfo webElementInfo) {
        this.webElementInfo = webElementInfo;
    }

    public WebElementInfo getElementInfo() {
        return webElementInfo;
    }

    public BaseWebElementImpl(WebDriver driver, WebElementInfo webElementInfo) {
        this.driver = driver;
        this.webElementInfo = webElementInfo;
        initLocator(this.webElementInfo.getLocatorType(), this.webElementInfo.getLocatorValue());
        initWaitStrategy();
    }

    private void initWaitStrategy() {
        WaitStrategy waitStrategy = this.webElementInfo.getStrategy();
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

    private void initLocator(WebLocatorType webLocatorType, String locatorValue) {
        switch (webLocatorType) {
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

    public WebElement waitUntil(final Function<By, ExpectedCondition<org.openqa.selenium.WebElement>> condition)  {
        return waitUntil(condition, timeout);
    }


    public WebElement getElement() {
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

    @Override
    public boolean isPresent(int timeout) {
        return false;
    }

    private <V> V waitUntil(final Function<By, ExpectedCondition<V>> condition, long timeout){
        return getWait(timeout).until(condition.apply(getLocator()));
    }

    public List<org.openqa.selenium.WebElement> waitUntilAll(final Function<By, ExpectedCondition<List<org.openqa.selenium.WebElement>>> condition) {
        return waitUntilAll(condition, timeout);
    }

    private List<org.openqa.selenium.WebElement> waitUntilAll(final Function<By, ExpectedCondition<List<org.openqa.selenium.WebElement>>> condition, long timeout)   {
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
    public void click() {
        waitUntil(waitForElement).click();
    }

    @Override
    public void click(long timeout) {
        waitUntil(waitForElement, timeout).click();
    }


    @Override
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
        String newLocator = String.format(this.webElementInfo.getLocatorValue(), eventName);
        initLocator(this.webElementInfo.getLocatorType(), newLocator);
        return this;
    }
    private class WaitFor {

        private ExpectedCondition<Boolean> state;
        private ExpectedCondition<org.openqa.selenium.WebElement> element;
        private ExpectedCondition<List<org.openqa.selenium.WebElement>> listElements;

        public ExpectedCondition<org.openqa.selenium.WebElement> visibility() {
            return ExpectedConditions.visibilityOfElementLocated(getLocator());
        }

        public ExpectedCondition<org.openqa.selenium.WebElement> clickable() {
            return ExpectedConditions.elementToBeClickable(getLocator());
        }

        public ExpectedCondition<org.openqa.selenium.WebElement> presence() {
            return ExpectedConditions.presenceOfElementLocated(getLocator());
        }


    }
}
