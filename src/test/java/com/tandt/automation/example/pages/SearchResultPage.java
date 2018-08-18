package com.tandt.automation.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tandt.automation.example.BasePage;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class SearchResultPage extends BasePage<SearchResultPage> {

    @FindBy(id = "resultStats")
    WebElement result;

    public SearchResultPage() {
        super();
    }

    public SearchResultPage open(String textToSearch){
        new HomePage().open().search(textToSearch);
        return this;
    }


    public String getResultCount(){
        return result.getText();
    }
}
