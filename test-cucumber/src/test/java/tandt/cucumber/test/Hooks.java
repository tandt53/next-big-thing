package tandt.cucumber.test;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Inject
    DriverHooks driverHooks;

    @Before(value = "@Mobile")
    public void beforeMobileScenario(){
        System.out.println("before mobile scenario");
//        driverHooks.iOpenApplication();
    }

    @After(value = "@Mobile")
    public void afterMobileScenario(){
        System.out.println("after mobile scenario");
    }

    @Before(value = "@Web")
    public void beforeWebScenario(){
        System.out.println("before web scenario");
//        driverHooks.iOpenBrowser();
    }

    @After(value = "@Web")
    public void afterWebScenario(){
        System.out.println("after web scenario");
    }
}
