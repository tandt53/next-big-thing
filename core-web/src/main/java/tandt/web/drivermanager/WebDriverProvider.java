package tandt.web.drivermanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import org.openqa.selenium.WebDriver;
import tandt.web.drivermanager.selector.WebDriverSelector;

public class WebDriverProvider implements Provider<WebDriver> {

    @Inject
    private WebDriverSelector selector;

    @Inject
    private Injector injector;

    @Override
    public WebDriver get() {
        return injector.getInstance(Key.get(DriverManager.class, Names.named(selector.get()))).initDriver();
    }
}
