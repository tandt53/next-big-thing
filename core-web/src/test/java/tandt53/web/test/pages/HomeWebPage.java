package tandt53.web.test.pages;

import tandt53.web.BaseWebPage;
import tandt53.web.PageFactory;
import tandt53.web.annotations.FindElement;
import tandt53.web.element.BaseWebElement;
import tandt53.web.element.LocatorType;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import static tandt53.web.element.WaitStrategy.VISIBILITY;

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
