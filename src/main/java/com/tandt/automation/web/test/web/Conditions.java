package com.tandt.automation.web.test.web;

import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Conditions {

    public static final Function<By, ExpectedCondition<WebElement>> CONDITION_VISIBILITY = ExpectedConditions::visibilityOfElementLocated;
    public static final Function<By, ExpectedCondition<WebElement>> CONDITION_CLICKABLE = ExpectedConditions::elementToBeClickable;
    public static final Function<By, ExpectedCondition<Boolean>> CONDITION_INVISIBILITY = ExpectedConditions::invisibilityOfElementLocated;
    public static final Function<By, ExpectedCondition<WebElement>> CONDITION_PRESENCE_OF_ELEMENT = ExpectedConditions::presenceOfElementLocated;
    public static final Function<By, ExpectedCondition<List<WebElement>>> CONDITION_PRESENCE_OF_ALL_ELEMENT = ExpectedConditions::visibilityOfAllElementsLocatedBy;
}
