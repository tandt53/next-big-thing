package com.tandt.automation.example.refactor.pages;

import com.tandt.automation.example.refactor.BasePage;
import com.tandt.automation.example.refactor.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class SearchResultPage extends BasePage<SearchResultPage> {

    @FindBy(id = "resultStats")
    WebElement result;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this, this.driver, this.wait);
    }

    public SearchResultPage open(String textToSearch){
        new HomePage(driver).open().search(textToSearch);
        return this;
    }


    public String getResultCount(){
        return result.getText();
    }
}
