package tandt.web.drivermanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import org.openqa.selenium.WebDriver;
import tandt.common.exceptions.CommonException;
import tandt.web.capability.CapabilityService;

import java.net.MalformedURLException;

public class DriverProvider implements Provider<WebDriver> {

    @Inject
    private CapabilityService service;

    @Inject
    Injector injector;

    @Override
    public WebDriver get() {
        service.loadCapabilities();
        service.getCapability();
        String env = service.getCapability().getValue(Constants.CAPABILITY_ENV);
        String browser = service.getCapability().getValue(Constants.CAPABILITY_BROWSER);

        try {
           return injector.getInstance(Key.get(DriverManager.class, Names.named(env + "." + browser))).initDriver();
        } catch (CommonException | MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
