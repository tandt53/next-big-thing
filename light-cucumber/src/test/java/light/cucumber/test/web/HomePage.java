package light.cucumber.test.web;

import light.ui.element.Element;
import light.ui.element.WaitStrategy;
import light.web.BaseWebPage;
import light.web.annotations.FindElement;
import light.web.element.WebLocatorType;
import org.openqa.selenium.support.FindBy;

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
