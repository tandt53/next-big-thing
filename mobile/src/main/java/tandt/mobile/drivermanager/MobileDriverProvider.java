package tandt.mobile.drivermanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import tandt.mobile.drivermanager.selector.MobileDriverSelector;

public class MobileDriverProvider implements Provider<MobileDriverManager> {

    @Inject
    MobileDriverSelector selector;

    @Inject
    Injector injector;

    @Override
    public MobileDriverManager get() {

        System.out.println("Selector: " + selector.get());
        return injector.getInstance(Key.get(MobileDriverManager.class, Names.named(selector.get())));
    }
}
