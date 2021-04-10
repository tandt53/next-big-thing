//package tandt.mobile.test.test;
//
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//import tandt.common.exceptions.CommonException;
//import tandt.dataprovider.exceptions.PropertiesException;
//import tandt.mobile.BaseTest;
//import tandt.mobile.annotations.Android;
//import tandt.mobile.drivermanager.MobileDriverManager;
//import tandt.mobile.test.pages.MyApplicationPage;
//
//import java.net.MalformedURLException;
//
//public class MyApplicationTest extends BaseTest<MyApplicationTest> {
//
//    @Android
//    private MobileDriverManager driverManager;
//
//    private MyApplicationPage page;
//
//    public MyApplicationTest() throws IllegalAccessException {
//
//    }
//
//    @BeforeClass
//    public void setupClass() throws PropertiesException, CommonException, MalformedURLException {
//        page = new MyApplicationPage(driverManager.initDriver());
//    }
//
//    @Test
//    public void loginTest(){
//        page.login("admin", "admin", true);
//        Assert.assertEquals("Login success", page.getMessage());
//    }
//}
