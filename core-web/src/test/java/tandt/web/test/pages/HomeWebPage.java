package tandt.web.test.pages;

import org.openqa.selenium.Keys;
import tandt.commontest.Prop;
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

    @FindElement(type = WebLocatorType.XPATH, value = "_fZl")
    private Element searchButton;

    @Prop("nbt.selenium.browserName")
    private String browser;

    @Prop("extra")
    private String extra;

    public void search(String text) {
        System.out.println("extra: " + extra);
        System.out.println("browser: " + browser);
        searchField.setText(Keys.SHIFT, text);
        searchField.submit();
    }

}
