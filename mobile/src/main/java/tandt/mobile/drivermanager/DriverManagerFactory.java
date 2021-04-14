package tandt.mobile.drivermanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import ui.driverselector.DriverSelector;

public class DriverManagerFactory {

    private static ThreadLocal<DriverManager> manager = new ThreadLocal<>();

    @Inject
    Injector injector;

    @Inject
    DriverSelector selector;

    /**
     * Init driver manager based on expected browser type: chrome, firefox, safari
     *
     * @param driverType
     * @return
     */
    public DriverManager getDriverManager(String driverType) {
        DriverManager m = injector.getInstance((Key.get(DriverManager.class, Names.named(driverType))));
        manager.set(m);
        m.initDriver();
        return m;
    }

    public DriverManager getDriverManager() {
        DriverManager m = injector.getInstance((Key.get(DriverManager.class, Names.named(selector.get()))));
        manager.set(m);
        m.initDriver();
        return m;
    }
}
