package com.tandt53.automation.web.test.test;

import com.tandt53.automation.web.BaseTest;
import com.tandt53.automation.web.BrowserFactory;
import com.tandt53.automation.web.annotations.Safari;
import com.tandt53.automation.web.drivermanager.DriverManager;
import com.tandt53.automation.web.drivermanager.DriverManagerFactory;
import com.tandt53.automation.web.test.pages.HomePage;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomeChromeTest extends BaseTest<HomeChromeTest> {


    HomePage homePage;

    @Safari
    protected DriverManager driver;

    public HomeChromeTest() throws IllegalAccessException {
//        driver = DriverManagerFactory.getDriverManager("remote");
        BrowserFactory.initPages(this);
    }

    @BeforeTest
    public void setup() throws IOException {
//        homePage = new HomePage(driver.initDriver("safari", new URL("http://10.124.56.123:4444/wd/hub"), new SafariOptions()));
        homePage = new HomePage(driver.initDriver());
    }

    @AfterTest
    public void teardown() {
        homePage.close();

        driver.initDriver().quit();
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

    @Test
    public void checkDriver2() {
        homePage.open().search("Checking");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkDriver3() {
        homePage.open().search("Checking");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkDriver4() {
        homePage.open().search("Checking");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
