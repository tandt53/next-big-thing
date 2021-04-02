package tandt.web.test.test;

import tandt.common.exceptions.CommonException;
import tandt.web.BaseTest;
import tandt.web.test.pages.HomeWebPage;
import tandt.web.drivermanager.DriverManager;
import tandt.web.drivermanager.DriverManagerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomeChromeTest extends BaseTest<HomeChromeTest> {


    private HomeWebPage homePage;

    protected DriverManager driver;

    public HomeChromeTest() throws IllegalAccessException {
        driver = DriverManagerFactory.getDriverManager("chrome");
    }

    @BeforeTest
    public void setup() throws IOException, CommonException {
        homePage = new HomeWebPage(driver.initDriver());
    }

    @AfterTest
    public void teardown() {
        homePage.close();

        driver.getDriver().quit();
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