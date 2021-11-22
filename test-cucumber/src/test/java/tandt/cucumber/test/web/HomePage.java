package tandt.cucumber.test.web;

import org.openqa.selenium.support.FindBy;
import tandt.web.BaseWebPage;
import tandt.web.annotations.FindElement;
import tandt.web.element.WebLocatorType;
import ui.element.Element;
import ui.element.WaitStrategy;

public class HomePage extends BaseWebPage<HomePage> {

    @FindElement(type = WebLocatorType.XPATH, value = "//*[@class='gLFyf gsfi']", waitUntil = WaitStrategy.VISIBILITY)
    private Element searchField;

    @FindBy(id = "_fZl")
    private org.openqa.selenium.WebElement searchButton;

    public void search(String keyword) {
        searchField.setText(keyword);
        searchField.submit();
    }
}
