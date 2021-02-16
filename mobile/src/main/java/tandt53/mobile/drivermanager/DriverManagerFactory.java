package tandt53.mobile.drivermanager;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;

public class DriverManagerFactory {

    private static ThreadLocal<MobileDriverManager> manager = new ThreadLocal<>();

    /**
     * Init driver manager based on expected browser type: chrome, firefox, safari
     *
     * @param driverType
     * @return
     */
    public static MobileDriverManager getDriverManager(String driverType) {
        Injector injector = Guice.createInjector(new MobileDriverBinder());
        MobileDriverManager m = injector.getInstance((Key.get(MobileDriverManager.class, Names.named(driverType))));
        manager.set(m);
        return m;
    }

    public static MobileDriverManager getDriverManager() {
        Injector injector = Guice.createInjector(new MobileDriverBinder());
        String browser = CapabilityManager.getPlatformName();
        MobileDriverManager m = injector.getInstance((Key.get(MobileDriverManager.class, Names.named(browser))));
        manager.set(m);
        return m;

    }
}
