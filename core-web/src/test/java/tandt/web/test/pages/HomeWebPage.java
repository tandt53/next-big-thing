package tandt.web.test.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import tandt.web.BaseWebPage;
import tandt.web.annotations.FindElement;
import tandt.web.element.WebLocatorType;
import ui.element.Element;
import ui.element.WaitStrategy;


/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomeWebPage extends BaseWebPage<HomeWebPage> {

    public HomeWebPage() {
        url = "http://google.com.vn";
    }

    @FindElement(type = WebLocatorType.XPATH, value = "//*[@class='gLFyf gsfi']", waitUntil = WaitStrategy.VISIBILITY)
    private Element searchField;

    @FindBy(id = "_fZl")
    private org.openqa.selenium.WebElement searchButton;

    public void search(String text) {
        searchField.setText(Keys.SHIFT, text);
        searchField.submit();
    }

}
