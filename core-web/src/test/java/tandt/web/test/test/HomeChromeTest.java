package tandt.web.test.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import tandt.common.exceptions.CommonException;
import tandt.web.DriverBinder;
import tandt.web.BaseTest;
import tandt.web.test.pages.HomeWebPage;

import java.io.IOException;

/**
 * Created by thetan.do on 12/28/2016.
 */

@Guice(modules = DriverBinder.class)
public class HomeChromeTest extends BaseTest {

    private HomeWebPage homePage;

    @BeforeTest
    public void setup() throws IOException, CommonException {
        homePage = page(HomeWebPage.class);
    }

    @AfterTest
    public void teardown() {
        homePage.close();
    }

    @Test
    public void checkSearch() {
        homePage.open().search("Checking");
    }

    @Test
    public void checkDriver() {
        homePage.open().search("Checking");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
