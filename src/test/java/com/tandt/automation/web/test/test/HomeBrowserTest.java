package com.tandt.automation.web.test.test;

import com.tandt.automation.web.BaseTest;
import com.tandt.automation.web.BrowserFactory;
import com.tandt.automation.web.annotations.Browser;
import com.tandt.automation.web.drivermanager.DriverManager;
import com.tandt.automation.web.test.pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomeBrowserTest extends BaseTest<HomeBrowserTest> {

    HomePage homePage;

    @Browser(value = "chrome")
    protected DriverManager driver;

    public HomeBrowserTest() throws IllegalAccessException {
        BrowserFactory.initPages(this);
    }

    @BeforeTest
    public void setup() throws IOException {
        homePage = new HomePage(driver.getDriver());
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
