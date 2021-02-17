package tandt.web.element;

import tandt.web.annotations.Clocking;
import org.openqa.selenium.*;

import java.util.List;

public interface BaseWebElement {

    @Clocking
    void setText(CharSequence... text);

    @Clocking
    String getText();

    @Clocking
    String getText(long timeout);

    @Clocking
    void click();

    @Clocking
    void click(long timeout);

    @Clocking
    void clearText();

    @Clocking
    void clearText(long timeout);

    @Clocking
    boolean isDisplayed();

    @Clocking
    boolean isDisplayed(long timeout);

    @Clocking
    void submit();

    @Clocking
    String getAttributeValue(String attribute);

    @Clocking
    String getTagName();

    @Clocking
    boolean isEnabled();

    @Clocking
    boolean isSelected();

    @Clocking
    Point getLocation();

    @Clocking
    Dimension getSize();

    @Clocking
    Rectangle getRect();

    @Clocking
    String getCssValue(String propertyName);

    BaseWebElement formatLocatorValue(String... eventName);

    @Clocking
    List<org.openqa.selenium.WebElement> getElements();

    @Clocking
    List<org.openqa.selenium.WebElement> getElements(long timeout);
}
