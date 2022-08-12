package onboarding.web.test.test;

import onboarding.commontest.TestContext;
import onboarding.commontest.TestModule;
import onboarding.web.BaseTest;
import onboarding.web.WebModule;
import onboarding.web.test.pages.HomeWebPage;
import onboarding.web.test.pages.SearchResultPage;
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
    }

}
