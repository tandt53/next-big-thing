package onboarding.cucumber.test;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Inject
    private DriverHooks driverHooks;

    @Before(value = "@Mobile", order = 0)
    public void beforeMobileScenario() {
        System.out.println("before mobile scenario");
        driverHooks.initMobile();
    }

    @After(value = "@Mobile", order = 9999)
    public void afterMobileScenario(Scenario scenario) {
        System.out.println("after mobile scenario 999");
        if (scenario.isFailed())
            driverHooks.takeScreenshotMobile(scenario.getName());
        driverHooks.closeMobileDriver();
    }

    @Before(value = "@Web", order = 0)
    public void beforeWebScenario() {
        System.out.println("before web scenario");
        driverHooks.initWeb();
    }

    @After(value = "@Web", order = 9999)
    public void afterWebScenario(Scenario scenario) {
        System.out.println("after web scenario");
        if (scenario.isFailed())
            driverHooks.takeScreenshotWeb(scenario.getName());
        driverHooks.closeWebDriver();
    }
}
