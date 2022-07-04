package onboarding.web.test.pages;

import onboarding.ui.element.Element;
import onboarding.web.BaseWebPage;
import onboarding.web.annotations.FindElement;
import onboarding.web.element.WebLocatorType;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class SearchResultPage extends BaseWebPage<SearchResultPage> {

    @FindElement(type = WebLocatorType.ID, value = "result-stats")
    private Element result;

    public SearchResultPage open(String textToSearch){
        return this;
    }

    public String getResultCount(){
        return result.getText();
    }
}
