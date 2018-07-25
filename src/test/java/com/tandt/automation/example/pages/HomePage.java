package com.tandt.automation.example.pages;

import com.tandt.automation.example.BasePage;
import com.tandt.automation.example.annotations.HtmlElement;
import com.tandt.automation.example.element.LocatorType;
import com.tandt.automation.example.element.TextInput;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomePage extends BasePage<HomePage> {


    public HomePage() {
        super();
        url = "http://google.com.vn";
        PLog.info("URL: " + url);
    }

//    @FindBy(id = "lst-ib")
    @HtmlElement(type=LocatorType.ID, value="lst-ib")
    TextInput searchField;

    @FindBy(id = "_fZl")
    WebElement searchButton;

    public void search(String text) {
        PLog.info("Search with text: " + text);
        searchField.setText(text);
        searchField.submit();
    }

}
