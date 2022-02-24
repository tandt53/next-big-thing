package ui.element;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import ui.annotations.Clocking;

import java.util.List;

public interface Element {
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

    Element formatLocatorValue(String... eventName);

    @Clocking
    List<WebElement> getElements();

    @Clocking
    List<WebElement> getElements(long timeout);

    boolean isPresent(int timeout);

    WebElement getElement();

    String getId();
}
