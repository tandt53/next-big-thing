package com.tandt.automation.example.refactor.pages;

import com.tandt.automation.example.BasePage;
import com.tandt.automation.example.annotations.FindElement;
import com.tandt.automation.example.element.Element;
import com.tandt.automation.example.element.LocatorType;
import com.tandt.automation.example.PageFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomePage extends BasePage<HomePage> {


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this, driver, wait);
        url = "http://google.com.vn";
        PLog.info("URL: " + url);
    }

    @FindElement(type = LocatorType.XPATH, value = "//*[@class='gLFyf gsfi']")
    Element searchField;

    @FindBy(id = "_fZl")
    WebElement searchButton;

    public void search(String text) {
        PLog.info("Search with text: " + text);
        searchField.setText(Keys.SHIFT, text);
        searchField.submit();
    }

}
