package onboarding.web.test.pages;

import onboarding.commontest.Prop;
import onboarding.ui.element.Element;
import onboarding.ui.element.WaitStrategy;
import onboarding.web.BaseWebPage;
import onboarding.web.annotations.FindElement;
import onboarding.web.element.WebLocatorType;


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

    @Prop("onboarding.selenium.browserName")
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