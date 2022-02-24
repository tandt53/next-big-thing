//package tandt.mobile.test.test.gesture;
//
//import com.google.inject.Inject;
//import io.appium.java_client.AppiumBy;
//import io.appium.java_client.AppiumDriver;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Guice;
//import org.testng.annotations.Test;
//import tandt.commontest.TestContext;
//import tandt.commontest.TestModule;
//import tandt.mobile.MobileModule;
//import tandt.mobile.drivermanager.DriverManager;
//import tandt.mobile.gesture.AndroidGesture;
//import tandt.mobile.test.test.Utils;
//
//import java.time.Duration;
//
//@Guice(modules = {TestModule.class, MobileModule.class})
//public class AndroidGestureTest {
//
//    @Inject
//    private AndroidGesture androidGesture;
//
//    @Inject
//    private DriverManager driverManager;
//
//    private AppiumDriver driver;
//
//    @BeforeMethod
//    public void setUp() {
//        TestContext.getInstance().getConfiguration().add("nbt.appium.app", "/Users/tandt1/projects/next-big-thing/mobile/input/apps/ApiDemos-debug.apk");
//        driver = driverManager.getDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        driverManager.quit();
//    }
//
//    @Test
//    public void testScroll() {
//        driver.findElement(AppiumBy.androidUIAutomator("text(\"Views\")")).click();
//        Utils.delay(2000);
//        androidGesture.scroll();
//    }
//
//}
//
