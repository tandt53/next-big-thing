//package light.mobile.test.test.gesture;
//
//import com.google.inject.Inject;
//import io.appium.java_client.AppiumBy;
//import io.appium.java_client.AppiumDriver;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Guice;
//import org.testng.annotations.Test;
//import light.commontest.TestContext;
//import light.commontest.TestModule;
//import light.mobile.MobileModule;
//import light.mobile.drivermanager.DriverManager;
//import light.mobile.gesture.AndroidGesture;
//import light.mobile.test.test.Utils;
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
//        TestContext.getInstance().getConfiguration().add("light.appium.app", "/Users/tandt1/projects/next-big-thing/mobile/input/apps/ApiDemos-debug.apk");
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
