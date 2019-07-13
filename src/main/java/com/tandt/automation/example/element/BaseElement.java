package com.tandt.automation.example.element;

import com.tandt.automation.example.annotations.Clocking;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.function.Function;

public interface BaseElement {

    @Clocking
    void setText(String text);

    @Clocking
    String getText();

    @Clocking
    String getText(BaseElement element);

    @Clocking
    void click();

    @Clocking
    void clearText();

    @Clocking
    boolean isDisplayed();

    @Clocking
    void submit();

    WebElement waitUntil(Function<By, ExpectedCondition<WebElement>> conditionVisibility);

    BaseElement getElement(String... eventName);

}
