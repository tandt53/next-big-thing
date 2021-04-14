package tandt.mobile.drivermanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import io.appium.java_client.MobileDriver;
import tandt.mobile.drivermanager.selector.MobileDriverSelector;

public class DriverProvider implements Provider<MobileDriver> {

    @Inject
    MobileDriverSelector selector;

    @Inject
    Injector injector;

//    @Override
//    public DriverManager get() {
//        return injector.getInstance(Key.get(DriverManager.class, Names.named(selector.get())));
//    }

    @Override
    public MobileDriver get() {
        return injector.getInstance(Key.get(DriverManager.class, Names.named(selector.get()))).initDriver();
    }
}
