package com.tandt.automation.example.pages;

import com.tandt.automation.example.BasePage;

import com.tandt.automation.example.annotations.FindElement;
import com.tandt.automation.example.element.BaseElement;
import com.tandt.automation.example.element.LocatorType;
import org.openqa.selenium.Keys;
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

    @FindElement(type = LocatorType.XPATH, value = "//*[@class='gLFyf gsfi']")
    BaseElement searchField;

    @FindBy(id = "_fZl")
    WebElement searchButton;

    public void search(String text) {
        PLog.info("Search with text: " + text);
        searchField.setText(Keys.SHIFT, text);
        searchField.submit();
    }

}
