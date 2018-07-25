package com.tandt.automation.example.test;

import com.tandt.automation.example.BaseTest;
import com.tandt.automation.example.pages.SearchResultPage;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class SearchResultTest extends BaseTest<SearchResultTest> {

    SearchResultPage searchResultPage;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws IOException {
        searchResultPage = new SearchResultPage();
        searchResultPage.open("Checking");
        searchResultPage.waitSafety(1000);

    }

    @AfterTest
    public void teardown() {
        searchResultPage.close();
    }

    @Test
    public void checkResultCount() {
        Assert.assertEquals(searchResultPage.getResultCount(), "Khoảng 320.000.000 kết quả (0,75 giây) ");
    }
}
