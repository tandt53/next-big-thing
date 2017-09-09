package com.thetan.automation.example.test;

import com.thetan.automation.example.BaseTest;
import com.thetan.automation.example.pages.ConnectScreen;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class ConnectScreenTest extends BaseTest {

    AndroidDriver driver;
    ConnectScreen connectScreen;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws IOException {
        driver = (AndroidDriver) getDriver(browser);
        connectScreen = new ConnectScreen(driver);
    }

    @AfterTest
    public void teardown() {
        connectScreen.close();
    }

    @Test
    public void testConnectServer() throws InterruptedException {
        Thread.sleep(2000);

        Set<String> contextHandles = driver.getContextHandles();
        String contextWeb = null;
        for (String s : contextHandles) {
            if (s.contains("WEBVIEW_")) {
                contextWeb = s;
            }
        }

        driver.context(contextWeb);
        ConnectScreen connectScreen = new ConnectScreen((AndroidDriver) driver);

        connectScreen.setServerAddress("172.18.114.22");
        connectScreen.setPort("8083");
        connectScreen.connect();

        Thread.sleep(2000);

        Assert.assertEquals("Login", connectScreen.getToolbarText());

    }
}
