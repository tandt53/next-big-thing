package com.thetan.automation.example;

import com.thetan.automation.example.pages.HomePage;
import com.thetan.automation.example.utils.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomePageTest extends AbstractTestcase {

    WebDriver driver;
    HomePage homePage;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) {
        driver = getDriver(browser);
        homePage = new HomePage(driver);
    }

    @AfterTest
    public void teardown() {
        homePage.close();
    }

    @Test
    public void checkDriver() {
        homePage.open()
                .search("Checking");
    }

    @Test
    public void checkSearch() {
        homePage.open()
                .search("Checking");
    }
}
