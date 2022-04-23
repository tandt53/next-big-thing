package light.web.test.pages;

import light.web.BaseWebPage;
import light.web.element.WebLocatorType;
import light.web.annotations.FindElement;
import light.ui.element.Element;

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
