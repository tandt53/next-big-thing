package tandt.web.drivermanager;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import tandt.web.DriverBinder;
import tandt.web.capability.CapabilityService;

public class DriverManagerFactory {

    private static ThreadLocal<DriverManager> manager = new ThreadLocal<>();

    /**
     * Init driver manager based on expected browser type: chrome, firefox, safari
     *
     * @param driverType
     * @return
     */
    public static DriverManager getDriverManager(String driverType) {
        Injector injector = Guice.createInjector(new DriverBinder());
        DriverManager m = injector.getInstance((Key.get(DriverManager.class, Names.named(driverType))));
        manager.set(m);
        return m;
    }

    public static DriverManager getDriverManager() {
        Injector injector;
        injector = Guice.createInjector(new DriverBinder());
        CapabilityService service = injector.getInstance(CapabilityService.class);
        service.loadCapabilities();

        String browser = service.getCapability().getValue("browser");
        String env = service.getCapability().getValue("env");
        String key = env + "." + browser;
        DriverManager m = injector.getInstance((Key.get(DriverManager.class, Names.named(key))));
        manager.set(m);
        return m;

    }
}
