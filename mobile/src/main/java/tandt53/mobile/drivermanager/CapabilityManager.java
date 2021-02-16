package tandt53.mobile.drivermanager;

import tandt53.common.Log;
import tandt53.dataprovider.exceptions.PropertiesException;
import tandt53.dataprovider.properties.PropertiesLoader;

import static tandt53.mobile.drivermanager.Constants.*;


public class CapabilityManager {
    private static Log capLog = new Log(CapabilityManager.class);
    private static ThreadLocal<Capability> extraCaps = new ThreadLocal<>();
    private static ThreadLocal<Capability> finalCaps = new ThreadLocal<>();

    CapabilityManager() {

    }

    public static void addExtraCapability(Capability cap) {
        extraCaps.get().addMap(cap.getMap());
    }

    public static void addExtraCapability(String key, String value) {
        extraCaps.get().addMap(key, value);
    }

    public static Capability getExtraCapability() {
        return extraCaps.get();
    }

    public static Capability loadCaps() throws PropertiesException {

        String propertyFile = System.getProperty(CLI_PARAM_CONFIG_MOBILE);
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = MOBILE_CONFIG_FILE;
        }

        Capability capFromFile;
        Capability capFromCommandLine;
        Capability extraCapFromCode = extraCaps.get();
        // load properties from file
        try {
            capFromFile = new Capability(propertyFile);
        } catch (PropertiesException e) {
            capLog.info("Unable to find config file at " + propertyFile + ".  empty caps will be loaded.");
            capFromFile = new Capability();
        }

        // load properties from command line arguments
        capFromCommandLine = new Capability(cliCapabilities, CLI_PARAMETER_PREFIX_MOBILE);

        // load properties from extra code
        finalCaps.set(capFromFile.addCapability(capFromCommandLine)
                .addCapability(extraCapFromCode));

        return finalCaps.get();

    }

    public static String getPlatformName() {
        // Get parameter from command line
        String propertyFile = System.getProperty(CLI_PARAM_CONFIG_MOBILE);
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = MOBILE_CONFIG_FILE;
        }

        try {
            return PropertiesLoader.getProperty(propertyFile, CAPABILITY_PLATFORM_NAME).toString();
        } catch (PropertiesException e) {
            return DRIVER_TYPE_ANDROID;
        }

    }


}
