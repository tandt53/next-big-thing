package tandt.web.test.pages;

import tandt.web.BaseWebPage;
import tandt.web.PageFactory;
import tandt.web.annotations.FindElement;
import tandt.web.element.BaseWebElement;
import tandt.web.element.LocatorType;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import static tandt.web.element.WaitStrategy.VISIBILITY;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomeWebPage extends BaseWebPage<HomeWebPage> {


    public HomeWebPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this, this.driver);
        url = "http://google.com.vn";
        PLog.info("URL: " + url);
    }

    @FindElement(type = LocatorType.XPATH, value = "//*[@class='gLFyf gsfi']", waitUntil = VISIBILITY)
    private BaseWebElement searchField;

    @FindBy(id = "_fZl")
    private org.openqa.selenium.WebElement searchButton;

    public void search(String text) {
        PLog.info("Search with text: " + text);
        searchField.setText(Keys.SHIFT, text);
        searchField.submit();
    }

}
