package tandt.cucumber.test;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import tandt.common.exceptions.CommonException;
import tandt.web.drivermanager.DriverManagerFactory;

import java.net.MalformedURLException;

@ScenarioScoped
public class DriverHooks {


    @Inject
    private DriverManagerFactory webDriver;

    public void iOpenBrowser() throws MalformedURLException, CommonException {
        webDriver.getDriverManager();
    }

    public void mobile(){

    }
}
