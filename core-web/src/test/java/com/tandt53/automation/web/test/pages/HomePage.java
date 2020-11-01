package com.tandt53.automation.web.test.pages;

import com.tandt53.automation.web.BasePage;
import com.tandt53.automation.web.PageFactory;
import com.tandt53.automation.web.annotations.FindElement;
import com.tandt53.automation.web.element.Element;
import com.tandt53.automation.web.element.LocatorType;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomePage extends BasePage<HomePage> {


//    @Inject
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this, wait);
        url = "http://google.com.vn";
        PLog.info("URL: " + url);
    }

//    @Inject
//    public HomePage(DriverManager driverManager) {
//        super(driverManager.getDriver());
//        PageFactory.initElements(this, wait);
//        url = "http://google.com.vn";
//        PLog.info("URL: " + url);
//    }

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
