package tandt.web.test.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import tandt.web.BaseWebPage;
import tandt.web.ElementFactory;
import tandt.web.annotations.FindElement;
import tandt.web.element.WebLocatorType;
import ui.element.Element;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class SearchResultPage extends BaseWebPage<SearchResultPage> {

    @FindElement(type = WebLocatorType.ID, value = "result-stats")
    private Element result;

    @Inject
    public SearchResultPage(WebDriver driver) {
        ElementFactory.initElements(driver, this);
    }

    public SearchResultPage open(String textToSearch){
        return this;
    }

    public String getResultCount(){
        return result.getText();
    }
}
