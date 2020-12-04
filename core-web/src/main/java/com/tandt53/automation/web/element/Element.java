package com.tandt53.automation.web.element;

import com.tandt53.automation.web.annotations.Clocking;
import org.openqa.selenium.*;

import java.util.List;

public interface Element {

    @Clocking
    void setText(CharSequence... text);

    @Clocking
    String getText();

    @Clocking
    String getText(long timeout);

    @Clocking
    String getText(Element element);

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

    Element getElement(String... eventName);

    @Clocking
    List<WebElement> getElements();

    @Clocking
    List<WebElement> getElements(long timeout);
}
