package onboarding.mobile.gesture;

import com.google.inject.Inject;
import io.appium.java_client.AppiumDriver;
import onboarding.mobile.drivermanager.DriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;

import java.util.HashMap;
import java.util.Map;

public class AndroidGesture {

    @Inject
    private DriverManager driverManager;

    public boolean scroll() {
        AppiumDriver driver = driverManager.getDriver();
        Dimension size = driver.manage().window().getSize();
        Point p = driver.manage().window().getPosition();
        int left = (int) (p.x + size.width * 0.2);
        int top = (int) (p.y + size.height * 0.2);
        int right = (int) (left + size.width * 0.8);
        int bottom = (int) (top + size.height * 0.8);

//        Map<String, Object> params = new HashMap<>();
//        params.put("left", left);
//        params.put("top", top);
//        params.put("right", right);
//        params.put("bottom", bottom);
//        params.put("steps", 100);
//        params.put("direction", "down");
//        params.put("percent", 1.0);

//        return (Boolean) ((JavascriptExecutor) driver)
//                .executeScript("mobile: scrollGesture", params);

        Map<String, Object> args = new HashMap<>();
        args.put("steps", 100);
        args.put("direction", "down");
        args.put("percent", 1.0);

        Map<String, Object> area = new HashMap<>();
        area.put("left", 100);
        area.put("top", 100);
        area.put("right", 200);
        area.put("bottom", 200);
        args.put("area", area);

        args.put("left", 100);
        args.put("top", 100);
        args.put("right", 200);
        args.put("bottom", 200);

        return (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",  args);
    }
}
