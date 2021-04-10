package tandt.web.drivermanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import org.openqa.selenium.WebDriver;
import tandt.common.exceptions.CommonException;
import tandt.web.drivermanager.selector.WebDriverSelector;

import java.net.MalformedURLException;

public class WebDriverProvider implements Provider<WebDriver> {

    @Inject
    WebDriverSelector selector;

    @Inject
    Injector injector;

    @Override
    public WebDriver get() {

        try {
            System.out.println("Selector: " + selector.get());
            return injector.getInstance(Key.get(DriverManager.class, Names.named(selector.get()))).initDriver();
        } catch (CommonException | MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
