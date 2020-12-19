package com.tandt53.mobile.test.test;

import com.tandt53.common.exceptions.CommonException;
import com.tandt53.dataprovider.exceptions.PropertiesException;
import com.tandt53.mobile.BaseTest;
import com.tandt53.mobile.annotations.Android;
import com.tandt53.mobile.drivermanager.MobileDriverManager;
import com.tandt53.mobile.test.pages.MyApplicationPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class MyApplicationTest extends BaseTest<MyApplicationTest> {


    @Android
    private MobileDriverManager driverManager;

    private MyApplicationPage page;

    public MyApplicationTest() throws IllegalAccessException {

    }

    @BeforeClass
    public void setupClass() throws PropertiesException, CommonException, MalformedURLException {
        page = new MyApplicationPage(driverManager.initDriver());
    }

    @Test
    public void loginTest(){
        page.login("admin", "admin", true);
        Assert.assertEquals("Login success", page.getMessage());
    }
}
