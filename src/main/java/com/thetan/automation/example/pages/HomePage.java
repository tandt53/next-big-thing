package com.thetan.automation.example.pages;

import com.thetan.automation.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomePage extends AbstractPage<HomePage> {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        url = "http://google.com.vn";
    }

    @FindBy(id = "lst-ib")
    WebElement searchField;

    @FindBy(id = "_fZl")
    WebElement searchButton;

    public void search(String text) {
        searchField.sendKeys(text);
        searchButton.click();
    }

}
