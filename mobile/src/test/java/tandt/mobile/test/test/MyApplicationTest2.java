package tandt.mobile.test.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import tandt.common.exceptions.CommonException;
import tandt.dataprovider.exceptions.PropertiesException;
import tandt.mobile.MobileModule;
import tandt.mobile.page.BaseTest;
import tandt.mobile.test.pages.HomePage;
import tandt.mobile.test.pages.HomePageBinder;

import java.net.MalformedURLException;


@Guice(modules = MobileModule.class)
public class MyApplicationTest2 extends BaseTest {

    private HomePage page;

    @BeforeClass
    public void setupClass() throws PropertiesException, CommonException, MalformedURLException {
        page();
        page = pageManager.getPage(HomePage.class, new HomePageBinder());
    }

    @Test
    public void loginTest() {
        page.login("admin", "admin", true);
        Assert.assertEquals(page.getMessage(), "Login success");
    }
}
