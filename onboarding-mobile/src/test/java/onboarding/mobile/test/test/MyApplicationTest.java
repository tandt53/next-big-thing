package onboarding.mobile.test.test;

import onboarding.commontest.TestModule;
import onboarding.mobile.MobileModule;
import onboarding.mobile.page.BaseTest;
import onboarding.mobile.test.pages.HomePage;
import onboarding.mobile.test.pages.HomePageBinder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;


@Guice(modules = {MobileModule.class, HomePageBinder.class, TestModule.class})
public class MyApplicationTest extends BaseTest {

    private HomePage page;

    @BeforeClass
    public void setupClass() {
        page = page(HomePage.class);
    }

    @Test
    public void loginTest() {
        page.login("admin", "admin", true);
        Assert.assertEquals(page.getMessage(), "Login success");
    }
}
