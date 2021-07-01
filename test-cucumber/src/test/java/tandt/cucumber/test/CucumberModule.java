package tandt.cucumber.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import io.cucumber.guice.CucumberModules;
import io.cucumber.guice.InjectorSource;
import tandt.mobile.MobileModule;
import tandt.web.WebModule;

/**
 * CucumberModule includes all needed modules for Guice.injector by cucumber-guice
 */
public class CucumberModule implements InjectorSource {

    @Override
    public Injector getInjector() {
        return Guice.createInjector(Stage.DEVELOPMENT,
                CucumberModules.createScenarioModule(),
                new WebModule(),
                new MobileModule());
    }
}
