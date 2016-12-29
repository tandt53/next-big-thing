package com.thetan.automation.example;

import com.thetan.automation.example.pages.SearchResultPage;
import com.thetan.automation.example.utils.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class SearchResultTest extends AbstractTestcase {

    WebDriver driver;
    SearchResultPage searchResultPage;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) {

        driver = getDriver(browser);

        searchResultPage = new SearchResultPage(driver);
        searchResultPage.open("Checking");
        searchResultPage.waitSafety(1000);

    }

    @AfterTest
    public void teardown() {
        searchResultPage.close();
    }

    @Test
    public void checkResultCount() {
        TLog.info("$$$$$$$$$$$$$$$$$$ checkResultCount $$$$$$$$$$$$$$$$$");
//        Assert.assertEquals(searchResultPage.getResultCount(), "Khoảng 320.000.000 kết quả (0,75 giây) ");
        TLog.info("$$$$$$$$$$$$$$$$$$  setup  $$$$$$$$$$$$$$$$$$$$$$");

    }
}
