package tandt.web.test.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import tandt.web.BaseWebPage;
import tandt.web.ElementFactory;
import tandt.web.annotations.FindElement;
import tandt.web.element.BaseWebElement;
import tandt.web.element.WebLocatorType;
import ui.element.WaitStrategy;


/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomeWebPage extends BaseWebPage<HomeWebPage> {

    public HomeWebPage() {
        ElementFactory.initElements(this);
        url = "http://google.com.vn";
    }

    @FindElement(type = WebLocatorType.XPATH, value = "//*[@class='gLFyf gsfi']", waitUntil = WaitStrategy.VISIBILITY)
    private BaseWebElement searchField;

    @FindBy(id = "_fZl")
    private org.openqa.selenium.WebElement searchButton;

    public void search(String text) {
        searchField.setText(Keys.SHIFT, text);
        searchField.submit();
    }

}
