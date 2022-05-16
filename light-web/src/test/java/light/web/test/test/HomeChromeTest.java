package light.web.test.test;

import light.commontest.TestContext;
import light.commontest.TestModule;
import light.commontest.configuration.Configuration;
import light.web.BaseTest;
import light.web.WebModule;
import light.web.test.pages.HomeWebPage;
import light.web.test.pages.SearchResultPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

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
        homePage.open().search("tandt53");
        System.out.println(searchResultPage.getResultCount());
    }

}
