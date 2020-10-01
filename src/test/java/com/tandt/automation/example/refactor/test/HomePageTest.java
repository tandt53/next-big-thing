package com.tandt.automation.example.refactor.test;

import com.tandt.automation.example.BaseTest;
import com.tandt.automation.example.refactor.pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class HomePageTest extends BaseTest<HomePageTest> {

	HomePage homePage;

	@BeforeTest
	public void setup() throws IOException {
		homePage = new HomePage(driver);
	}

	@AfterTest
	public void teardown() {
		homePage.close();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkDriver2() {
		homePage.open().search("Checking");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkDriver3() {
		homePage.open().search("Checking");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkDriver4() {
		homePage.open().search("Checking");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
