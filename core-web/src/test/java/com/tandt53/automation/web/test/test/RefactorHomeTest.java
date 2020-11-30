package com.tandt53.automation.web.test.test;

import com.tandt53.automation.web.BaseTest;
import com.tandt53.automation.web.BrowserFactory;
import com.tandt53.automation.web.annotations.Safari;
import com.tandt53.automation.web.drivermanager.Constants;
import com.tandt53.automation.web.drivermanager.DriverManager;
import com.tandt53.automation.web.drivermanager.DriverManagerFactory;
import com.tandt53.automation.web.drivermanager.options.Caps;
import com.tandt53.automation.web.test.pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class RefactorHomeTest extends BaseTest<RefactorHomeTest> {

    HomePage homePage;

//    @Safari
    protected DriverManager driver;

    public RefactorHomeTest() throws IllegalAccessException, MalformedURLException {
//        BrowserFactory.initPages(this);
    }

    @BeforeTest
    public void setup()  {
        String propertyFile = System.getProperty("config");
        Caps caps;
        if (propertyFile != null && propertyFile.isEmpty())
            caps = new Caps(propertyFile);
        else
            caps = new Caps();

        driver = DriverManagerFactory.getDriverManager(caps.getCapability(Constants.CAPABILITY_BROWSER).toString());

        homePage = new HomePage(driver.initDriver(caps.getCapabilities()));
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
