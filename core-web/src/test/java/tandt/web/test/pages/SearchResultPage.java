package tandt.web.test.pages;

import tandt.web.BaseWebPage;
import tandt.web.ElementFactory;
import tandt.web.annotations.FindElement;
import tandt.web.element.BaseWebElement;
import tandt.web.element.WebLocatorType;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class SearchResultPage extends BaseWebPage<SearchResultPage> {

    @FindElement(type = WebLocatorType.ID, value = "resultStats")
    private BaseWebElement result;

    public SearchResultPage() {
        ElementFactory.initElements(this);
    }

    public SearchResultPage open(String textToSearch){
        return this;
    }


    public String getResultCount(){
        return result.getText();
    }
}
