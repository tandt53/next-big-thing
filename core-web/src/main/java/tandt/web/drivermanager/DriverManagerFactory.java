//package tandt.web.drivermanager;
//
//import com.google.inject.Inject;
//import com.google.inject.Injector;
//import com.google.inject.Key;
//import com.google.inject.name.Names;
//import ui.driverselector.DriverSelector;
//
//public class DriverManagerFactory {
//
//    private DriverManager manager;
//
//    @Inject
//    Injector injector;
//
//    @Inject
//    DriverSelector selector;
//
//    /**
//     * Init driver manager based on expected browser type: chrome, firefox, safari
//     *
//     * @param driverType
//     * @return
//     */
//    public DriverManager getDriverManager(String driverType) {
//        if (manager == null) {
//            manager = injector.getInstance((Key.get(DriverManager.class, Names.named(driverType))));
//            manager.initDriver();
//        }
//        return manager;
//    }
//
//    public DriverManager getDriverManager() {
//        if (manager == null) {
//            manager = injector.getInstance((Key.get(DriverManager.class, Names.named(selector.get()))));
//            manager.initDriver();
//        }
//        return manager;
//    }
//
//}
