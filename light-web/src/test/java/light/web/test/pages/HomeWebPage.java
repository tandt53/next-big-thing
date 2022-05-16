package light.web.test.pages;

import light.commontest.Prop;
import light.ui.element.Element;
import light.ui.element.WaitStrategy;
import light.web.BaseWebPage;
import light.web.annotations.FindElement;
import light.web.element.WebLocatorType;


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

    @Prop("light.selenium.browserName")
    private String browser;

    @Prop("extra")
    private String extra;

    public void search(String text) {
        System.out.println("extra: " + extra);
        System.out.println("browser: " + browser);
        searchField.setText(text);
        searchField.submit();
    }

}
