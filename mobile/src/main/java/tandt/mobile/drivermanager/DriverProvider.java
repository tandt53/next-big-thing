package tandt.mobile.drivermanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import io.appium.java_client.MobileDriver;
import ui.driverselector.DriverSelector;

public class DriverProvider implements Provider<MobileDriver> {

    @Inject
    @Named("mobile")
    DriverSelector selector;

    @Inject
    Injector injector;

    @Override
    public MobileDriver get() {
        return injector.getInstance(Key.get(DriverManager.class, Names.named(selector.get()))).initDriver();
    }
}
