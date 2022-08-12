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
        driverHooks.initMobile();
    }

    @After(value = "@Mobile", order = 9999)
    public void afterMobileScenario(Scenario scenario) {
        if (scenario.isFailed())
            driverHooks.takeScreenshotMobile(scenario.getName());
        driverHooks.closeMobileDriver();
    }

    @Before(value = "@Web", order = 0)
    public void beforeWebScenario() {
        driverHooks.initWeb();
    }

    @After(value = "@Web", order = 9999)
    public void afterWebScenario(Scenario scenario) {
        if (scenario.isFailed())
            driverHooks.takeScreenshotWeb(scenario.getName());
        driverHooks.closeWebDriver();
    }
}
