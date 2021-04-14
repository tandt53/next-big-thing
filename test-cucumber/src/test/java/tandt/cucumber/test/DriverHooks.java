package tandt.cucumber.test;

import com.google.inject.Inject;
import com.google.inject.Injector;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class DriverHooks {


    @Inject
    private Injector injector;

    private WebDriver driver;

    public void iOpenBrowser() {
        driver = injector.getInstance(WebDriver.class);
    }

    public void mobile(){

    }
}
