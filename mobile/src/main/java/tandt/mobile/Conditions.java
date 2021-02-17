package tandt.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.function.Function;

public class Conditions {

    public static final Function<By, ExpectedCondition<WebElement>> VISIBILITY = ExpectedConditions::visibilityOfElementLocated;
    public static final Function<By, ExpectedCondition<WebElement>> CLICKABLE = ExpectedConditions::elementToBeClickable;
    public static final Function<By, ExpectedCondition<Boolean>> INVISIBILITY = ExpectedConditions::invisibilityOfElementLocated;
    public static final Function<By, ExpectedCondition<WebElement>> PRESENCE = ExpectedConditions::presenceOfElementLocated;
    public static final Function<By, ExpectedCondition<List<WebElement>>> PRESENCE_ALL = ExpectedConditions::presenceOfAllElementsLocatedBy;
    public static final Function<By, ExpectedCondition<List<WebElement>>> VISIBILITY_ALL = ExpectedConditions::visibilityOfAllElementsLocatedBy;

}
