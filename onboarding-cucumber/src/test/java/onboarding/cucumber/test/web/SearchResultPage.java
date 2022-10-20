package onboarding.cucumber.test.web;

import onboarding.ui.element.Element;
import onboarding.web.BaseWebPage;
import onboarding.web.annotations.FindElement;
import onboarding.web.element.WebLocatorType;

public class SearchResultPage extends BaseWebPage {

    @FindElement(type = WebLocatorType.ID, value = "result-stats")
    private Element result;

    public String getResult() {
        return result.getText();
    }
}
