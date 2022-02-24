package tandt.mobile.test.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import tandt.commontest.TestContext;
import tandt.commontest.TestModule;
import tandt.mobile.MobileModule;
import tandt.mobile.page.BaseTest;
import tandt.mobile.test.pages.HomePage;
import tandt.mobile.test.pages.HomePageBinder;


@Guice(modules = {MobileModule.class, HomePageBinder.class, TestModule.class})
public class MyApplication2Test extends BaseTest {

    private HomePage page;

    @BeforeClass
    public void setupClass() {
        TestContext.getInstance().getConfiguration().add("nbt.appium.app", "/Users/tandt1/projects/next-big-thing/mobile/input/apps/app-debug.apk");
        page = page(HomePage.class);
    }

    @Test
    public void loginTest() {
        page.login("admin", "admin", true);
        Assert.assertEquals(page.getMessage(), "Login success");
    }
}
