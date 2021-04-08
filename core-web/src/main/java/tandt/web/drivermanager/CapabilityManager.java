//package tandt.web.drivermanager;
//
//import tandt.dataprovider.exceptions.PropertiesException;
//import tandt.dataprovider.properties.PropertiesLoader;
//import tandt.web.capability.ICapability;
//
//
//public class CapabilityManager {
//
//    CapabilityManager() {
//    }
//
//    private static ThreadLocal<ICapability> extraCaps = new ThreadLocal<>();
//    private static ThreadLocal<ICapability> finalCaps = new ThreadLocal<>();
//
//    public static void addExtraCapability(Capability cap) {
//        extraCaps.get().addCapability(cap.getCapabilities());
//    }
//
//    public static void addExtraCapability(String key, String value) {
//        extraCaps.get().addCapability(key, value);
//    }
//
//    public static ICapability getExtraCapability() {
//        return extraCaps.get();
//    }
//
//    public static ICapability loadCaps() {
//
//        String propertyFile = System.getProperty("config.web");
//        if (propertyFile == null || propertyFile.isEmpty()) {
//            propertyFile = Constants.WEB_CONFIG_FILE;
//        }
//
//        ICapability capFromFile = new Capability();
//        ICapability capFromCommandLine = new Capability();
//        ICapability extraCapFromCode = extraCaps.get();
//        // load properties from file
//        try {
//            capFromFile.addCapability(propertyFile);
//        } catch (PropertiesException e) {
//            System.out.println("Unable to find config file at " + propertyFile + ".  empty caps will be loaded.");
//            capFromFile = new Capability();
//        }
//
//        // load properties from command line arguments
//        capFromCommandLine.addCapability(Constants.cliParameters, Constants.CLI_PARAMETER_PREFIX_WEB);
//
//
//        // load properties from extra code
//        finalCaps.set(capFromFile
//                .addCapability(capFromCommandLine)
//                .addCapability(extraCapFromCode));
//
//        return finalCaps.get();
//
//    }
//
//    public static String getBrowser() {
//        // Get parameter from command line
//        String propertyFile = System.getProperty(Constants.CLI_PARAM_CONFIG_WEB);
//        if (propertyFile == null || propertyFile.isEmpty()) {
//            propertyFile = Constants.WEB_CONFIG_FILE;
//        }
//
//        try {
//            return PropertiesLoader.getProperty(propertyFile, Constants.CAPABILITY_BROWSER).toString();
//        } catch (PropertiesException e) {
//            return Constants.DRIVER_TYPE_CHROME;
//        }
//
//    }
//
//    public static String getEnv() {
//        // Get parameter from command line
//        String propertyFile = System.getProperty(Constants.CLI_PARAM_CONFIG_WEB);
//        if (propertyFile == null || propertyFile.isEmpty()) {
//            propertyFile = Constants.WEB_CONFIG_FILE;
//        }
//
//        try {
//            return PropertiesLoader.getProperty(propertyFile, Constants.CAPABILITY_ENV).toString();
//        } catch (PropertiesException e) {
//            return Constants.ENV_LOCAL;
//        }
//    }
//
//}
