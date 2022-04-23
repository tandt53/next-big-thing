package light.cucumber.test.web;

import org.openqa.selenium.support.FindBy;
import light.web.BaseWebPage;
import light.web.annotations.FindElement;
import light.web.element.WebLocatorType;
import light.ui.element.Element;
import light.ui.element.WaitStrategy;

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