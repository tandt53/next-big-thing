package com.thetan.automation.example.pages;

import com.thetan.automation.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomePage extends BasePage<HomePage> {


    public HomePage() {
        super();
        url = "http://google.com.vn";
        PLog.info("URL: " + url);
    }

    @FindBy(id = "lst-ib")
    WebElement searchField;

    @FindBy(id = "_fZl")
    WebElement searchButton;

    public void search(String text) {
        PLog.info("Search with text: " + text);
        searchField.sendKeys(text);
        searchField.submit();
    }

}
