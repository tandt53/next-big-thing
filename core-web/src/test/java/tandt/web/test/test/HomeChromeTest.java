package tandt.web.test.test;

import com.google.inject.Inject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import tandt.web.BaseTest;
import tandt.web.WebModule;
import tandt.web.test.pages.HomeWebPage;
import tandt.web.test.pages.SearchResultPage;
import ui.capability.Capability;

/**
 * Created by thetan.do on 12/28/2016.
 */

@Guice(modules = WebModule.class)
public class HomeChromeTest extends BaseTest {

    private HomeWebPage homePage;
    private SearchResultPage searchResultPage;

    @Inject
    Capability capability;

    @BeforeTest
    public void setup() {
        homePage = page(HomeWebPage.class);
        searchResultPage = page(SearchResultPage.class);
    }

    @AfterTest
    public void teardown() {
        homePage.close();
    }

    @Test
    public void checkSearch() {
        homePage.open().search("Checking");
    }

}
