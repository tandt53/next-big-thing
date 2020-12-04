package com.tandt53.automation.web.test.test;

import com.tandt53.automation.common.exceptions.CommonException;
import com.tandt53.automation.web.BaseTest;
import com.tandt53.automation.web.drivermanager.DriverManager;
import com.tandt53.automation.web.drivermanager.DriverManagerFactory;
import com.tandt53.automation.web.test.pages.HomeWebPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class RemoteWebDriverTest extends BaseTest<RemoteWebDriverTest> {

    HomeWebPage homePage;

    protected DriverManager driver;

    public RemoteWebDriverTest() throws IllegalAccessException {
    }

    @BeforeTest
    public void setup() throws MalformedURLException, CommonException {
        driver = DriverManagerFactory.getDriverManager();
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


}
