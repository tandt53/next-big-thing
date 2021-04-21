package tandt.web.drivermanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.openqa.selenium.WebDriver;
import ui.driverselector.DriverSelector;

public class WebDriverProvider implements Provider<WebDriver> {

    @Inject
    @Named("web")
    private DriverSelector selector;

    @Inject
    private Injector injector;

    @Override
    public WebDriver get() {
        return injector.getInstance(Key.get(DriverManager.class, Names.named(selector.get()))).initDriver();
    }
}
