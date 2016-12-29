package com.thetan.automation.example.pages;

import com.thetan.automation.example.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class SearchResultPage extends AbstractPage<SearchResultPage> {

    WebDriver driver;

    @FindBy(id = "resultStats")
    WebElement result;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchResultPage open(String textToSearch){
        new HomePage(driver).open().search(textToSearch);
        return this;
    }


    public String getResultCount(){
        return result.getText();
    }
}
