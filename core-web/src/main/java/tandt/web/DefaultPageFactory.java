package tandt.web;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.openqa.selenium.WebDriver;

public class DefaultPageFactory implements PageFactory {

    private WebDriver driver;
    private Injector injector;
    @Inject
    public DefaultPageFactory(WebDriver driver, Injector injector) {
        this.driver = driver;
        this.injector = injector;
    }


    @Override
    public <TPage extends BaseWebPage<TPage>> TPage create(Class<? extends TPage> contract) {
        TPage page = injector.getInstance(contract);
        page.setDriver(driver);
        org.openqa.selenium.support.PageFactory.initElements(driver, page);
        return page;
    }
}
