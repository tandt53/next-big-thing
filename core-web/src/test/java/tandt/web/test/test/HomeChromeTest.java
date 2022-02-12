package tandt.web.test.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import tandt.commontest.TestContext;
import tandt.commontest.TestModule;
import tandt.commontest.configuration.Configuration;
import tandt.web.BaseTest;
import tandt.web.WebModule;
import tandt.web.test.pages.HomeWebPage;
import tandt.web.test.pages.SearchResultPage;

/**
 * Created by thetan.do on 12/28/2016.
 */

@Guice(modules = {WebModule.class, TestModule.class})
public class HomeChromeTest extends BaseTest {

    private HomeWebPage homePage;
    private SearchResultPage searchResultPage;

    private Configuration cap;

    @BeforeTest
    public void setup() {
        TestContext.getInstance().getConfiguration().add("extra", "extra cap");

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
        System.out.println(searchResultPage.getResultCount());
    }

}
