//package tandt.web.test.test;
//
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Guice;
//import org.testng.annotations.Test;
//import tandt.common.exceptions.CommonException;
//import tandt.web.BaseTest;
//import tandt.web.annotations.Safari;
//import tandt.web.drivermanager.DriverManager;
//import tandt.web.modules.CoreModule;
//import tandt.web.test.pages.HomeWebPage;
//
//import java.net.MalformedURLException;
//
///**
// * Created by thetan.do on 12/28/2016.
// */
//@Guice(modules = CoreModule.class)
//public class HomeChromeAnnotationTest extends BaseTest<HomeChromeAnnotationTest> {
//
//    private HomeWebPage homePage;
//
//    @Safari
//    protected DriverManager driver;
//
//    public HomeChromeAnnotationTest() throws IllegalAccessException {
//    }
//
//    @BeforeTest
//    public void setup() throws MalformedURLException, CommonException {
//        homePage = new HomeWebPage(driver.initDriver());
//    }
//
//    @AfterTest
//    public void teardown() {
//        homePage.close();
//        driver.getDriver().quit();
//    }
//
//    @Test
//    public void checkSearch() {
//        homePage.open().search("Checking");
//    }
//
//    @Test
//    public void checkDriver() {
//        homePage.open().search("Checking");
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
