package onboarding.web.test.test;

import onboarding.commontest.TestContext;
import onboarding.commontest.TestModule;
import onboarding.commontest.configuration.Configuration;
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

    private Configuration cap;

    @BeforeTest
    public void setup() {
        TestContext.getInstance().getConfiguration().add("extra", "extra cap");

        homePage = page(HomeWebPage.class);
        searchResultPage = page(SearchResultPage.class);

        // print all number from 0-100
//         1
//         2
//         3
//         4
//         5
//         6
//         7
//         8
//         9
//         ...
//         100
        // Write all solutions to print all values from 0 to N using for loop
        int N = 100;
        for (int i = 0; i < N; i++) {
            System.out.println(i);
        }

        int a[] = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};


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
