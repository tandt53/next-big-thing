package com.tandt53.automation.web.test.test;

import com.tandt53.automation.web.BaseTest;
import com.tandt53.automation.web.BrowserFactory;
import com.tandt53.automation.web.annotations.Chrome;
import com.tandt53.automation.web.annotations.FireFox;
import com.tandt53.automation.web.annotations.Safari;
import com.tandt53.automation.web.drivermanager.DriverManager;
import com.tandt53.automation.web.test.pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomeChromeAnnotationTest extends BaseTest<HomeChromeAnnotationTest> {

    HomePage homePage;

    @FireFox
    protected DriverManager driver;

    public HomeChromeAnnotationTest() throws IllegalAccessException, MalformedURLException {
//        BrowserFactory.initPages(this);
    }

    @BeforeTest
    public void setup() {
        homePage = new HomePage(driver.initDriver());
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
