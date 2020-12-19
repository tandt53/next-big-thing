package com.tandt53.web.test.test;

import com.tandt53.common.exceptions.CommonException;
import com.tandt53.web.BaseTest;
import com.tandt53.web.annotations.Safari;
import com.tandt53.web.drivermanager.DriverManager;
import com.tandt53.web.test.pages.HomeWebPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomeChromeAnnotationTest extends BaseTest<HomeChromeAnnotationTest> {

    private HomeWebPage homePage;

    @Safari
    protected DriverManager driver;

    public HomeChromeAnnotationTest() throws IllegalAccessException {
    }

    @BeforeTest
    public void setup() throws MalformedURLException, CommonException {
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
