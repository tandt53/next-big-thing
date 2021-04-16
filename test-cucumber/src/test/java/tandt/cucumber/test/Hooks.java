package tandt.cucumber.test;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Inject
    private DriverHooks driverHooks;

    @Before(value = "@Mobile", order = 0)
    public void beforeMobileScenario() {
        System.out.println("before mobile scenario");
//        driverHooks.iOpenApplication();
    }

    @After(value = "@Mobile", order = 999999)
    public void afterMobileScenario() {
        System.out.println("after mobile scenario 999");
        driverHooks.takeScreenshotMobile("");
        driverHooks.closeMobileDriver();
    }

    @Before(value = "@Web", order = 0)
    public void beforeWebScenario() {
        System.out.println("before web scenario");
//        driverHooks.iOpenBrowser();
    }

    @After(value = "@Web", order = 999999)
    public void afterWebScenario() {
        System.out.println("after web scenario");
        driverHooks.takeScreenshotWeb("");
        driverHooks.closeWebDriver();
    }
}
