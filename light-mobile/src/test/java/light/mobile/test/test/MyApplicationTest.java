//package light.mobile.test.test;
//
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//import light.common.exceptions.CommonException;
//import light.dataprovider.exceptions.PropertiesException;
//import light.mobile.BaseTest;
//import light.mobile.annotations.Android;
//import light.mobile.drivermanager.MobileDriverManager;
//import light.mobile.test.pages.MyApplicationPage;
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
//    public void setupClass() , CommonException, MalformedURLException {
//        page = new MyApplicationPage(driverManager.initDriver());
//    }
//
//    @Test
//    public void loginTest(){
//        page.login("admin", "admin", true);
//        Assert.assertEquals("Login success", page.getMessage());
//    }
//}
