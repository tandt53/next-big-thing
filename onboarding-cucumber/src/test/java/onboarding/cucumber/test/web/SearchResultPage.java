package onboarding.cucumber.test.web;

import onboarding.ui.element.Element;
import onboarding.web.annotations.FindElement;
import onboarding.web.element.WebLocatorType;
import onboarding.web.page.BasePage;

public class SearchResultPage extends BasePage {

    @FindElement(type = WebLocatorType.ID, value = "result-stats")
    private Element result;

    public String getResult() {
        return result.getText();
    }
}
