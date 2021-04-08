package tandt.web.drivermanager;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import tandt.web.capability.ICapabilityManagerService;
import tandt.web.modules.CoreModule;

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
        injector = Guice.createInjector(new CoreModule());
        ICapabilityManagerService service = injector.getInstance(ICapabilityManagerService.class);
        service.loadCapabilities();

        String browser = service.getCapability().getValue("browser");
        String env = service.getCapability().getValue("env");
        String key = env + "." + browser;
        DriverManager m = injector.getInstance((Key.get(DriverManager.class, Names.named(key))));
        manager.set(m);
        return m;

    }
}
