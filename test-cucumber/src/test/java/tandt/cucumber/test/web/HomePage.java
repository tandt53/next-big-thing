package tandt.cucumber.test.web;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import tandt.web.BaseWebPage;
import tandt.web.ElementFactory;
import tandt.web.annotations.FindElement;
import tandt.web.element.BaseWebElement;
import tandt.web.element.WebLocatorType;
import ui.element.WaitStrategy;

public class HomePage extends BaseWebPage<HomePage> {

    @FindElement(type = WebLocatorType.XPATH, value = "//*[@class='gLFyf gsfi']", waitUntil = WaitStrategy.VISIBILITY)
    private BaseWebElement searchField;

    @FindBy(id = "_fZl")
    private org.openqa.selenium.WebElement searchButton;

    public HomePage(){
        ElementFactory.initElements(this);
    }

    public void search(String keyword) {
        searchField.setText(Keys.SHIFT, keyword);
        searchField.submit();
    }
}
