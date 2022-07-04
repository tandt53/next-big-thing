package onboarding.mobile.drivermanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import onboarding.commontest.Prop;

public class DriverManagerFactory {

    private static DriverManager manager;

    @Inject
    private Injector injector;

    @Inject
    @Prop("onboarding.appium.platformName")
    private String platformName;

    /**
     * Init driver manager based on expected browser type: chrome, firefox, safari
     *
     * @param driverType
     * @return
     */
    public DriverManager getDriverManager(String driverType) {
        manager = injector.getInstance((Key.get(DriverManager.class, Names.named(driverType))));
        manager.initDriver();
        return manager;
    }

    public DriverManager getDriverManager() {
        DriverManager m = injector.getInstance((Key.get(DriverManager.class, Names.named(platformName))));
        m.initDriver();
        return manager;
    }
}
