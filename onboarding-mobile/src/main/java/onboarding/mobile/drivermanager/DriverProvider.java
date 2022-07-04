package onboarding.mobile.drivermanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import onboarding.commontest.Prop;

public class DriverProvider implements Provider<DriverManager> {

    @Inject
    @Prop("onboarding.appium.platformName")
    private String platformName;

    @Inject
    Injector injector;

    @Override
    public DriverManager get() {
        return injector.getInstance(Key.get(DriverManager.class, Names.named(platformName)));
    }
}
