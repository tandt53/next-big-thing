package light.cucumber.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import io.cucumber.guice.CucumberModules;
import io.cucumber.guice.InjectorSource;
import light.commontest.TestModule;
import light.cucumber.test.mobile.HomePageBinder;
import light.mobile.MobileModule;
import light.web.WebModule;

/**
 * CucumberModule includes all needed modules for Guice.injector by cucumber-guice
 */
public class CucumberModule implements InjectorSource {

    @Override
    public Injector getInjector() {
        return Guice.createInjector(Stage.DEVELOPMENT,
                CucumberModules.createScenarioModule(),
                new TestModule(),
                new WebModule(),
                new MobileModule(),
                new HomePageBinder());
    }
}
