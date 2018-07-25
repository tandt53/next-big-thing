package com.tandt.automation.example.test;

import com.tandt.automation.example.BaseTest;
import com.tandt.automation.example.pages.HomePage;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomePageTest extends BaseTest<HomePageTest> {

    HomePage homePage;

    @BeforeTest
    public void setup() throws IOException {
        homePage = new HomePage();
    }

    @AfterTest
    public void teardown() {
        homePage.close();
    }

    @Test
    public void checkDriver() {
        homePage.open()
                .search("Checking");
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    public void checkSearch() {
        homePage.open()
                .search("Checking");
    }
}
