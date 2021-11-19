package tandt.web.test.test;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import tandt.web.BaseTest;
import tandt.web.WebModule;
import tandt.web.customguice.PropsModule;
import tandt.web.test.pages.HomeWebPage;
import tandt.web.test.pages.SearchResultPage;
import tandt.common.configurations.capability.Capability;
import tandt.common.configurations.capability.CapabilityService;

/**
 * Created by thetan.do on 12/28/2016.
 */

@Guice(modules = {WebModule.class, PropsModule.class})
public class HomeChromeTest extends BaseTest {

    private HomeWebPage homePage;
    private SearchResultPage searchResultPage;

    @Inject
    @Named("web")
    CapabilityService capabilityService;

    private Capability cap;

    @BeforeTest
    public void setup() {
        cap = new ExtraCaps();
        cap.add("extra", "extra");
//        capabilityService = WebCapabilityService.getInstance();
        capabilityService.addCapability(cap);

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
