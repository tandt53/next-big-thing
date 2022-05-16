package light.mobile.test.test;

import light.commontest.TestModule;
import light.mobile.MobileModule;
import light.mobile.page.BaseTest;
import light.mobile.test.pages.HomePage;
import light.mobile.test.pages.HomePageBinder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;


@Guice(modules = {MobileModule.class, HomePageBinder.class, TestModule.class})
public class MyApplicationTest extends BaseTest {

    private HomePage page;

    @BeforeClass
    public void setupClass() {
//        TestContext.getInstance().getConfiguration().add("light.appium.app", "/Users/tandt1/projects/next-big-thing/mobile/input/apps/app-debug.apk");
        page = page(HomePage.class);
    }

    @Test
    public void loginTest() {
        page.login("admin", "admin", true);
        Assert.assertEquals(page.getMessage(), "Login success");
    }
}
