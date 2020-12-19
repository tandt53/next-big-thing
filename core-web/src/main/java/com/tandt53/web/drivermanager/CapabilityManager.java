package com.tandt53.web.drivermanager;

import com.tandt53.dataprovider.exceptions.PropertiesException;
import com.tandt53.dataprovider.properties.PropertiesLoader;


public class CapabilityManager {

    CapabilityManager() {
    }

    private static ThreadLocal<Capability> extraCaps = new ThreadLocal<>();
    private static ThreadLocal<Capability> finalCaps = new ThreadLocal<>();

    public static void addExtraCapability(Capability cap) {
        extraCaps.get().addMap(cap.getMap());
    }

    public static void addExtraCapability(String key, String value) {
        extraCaps.get().addMap(key, value);
    }


    public static Capability getExtraCapability() {
        return extraCaps.get();
    }

    public static Capability loadCaps() {

        String propertyFile = System.getProperty("config.mobile");
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = Constants.WEB_CONFIG_FILE;
        }

        Capability capFromFile;
        Capability capFromCommandLine;
        Capability extraCapFromCode = extraCaps.get();
        // load properties from file
        try {
            capFromFile = new Capability(propertyFile);
        } catch (PropertiesException e) {
            System.out.println("Unable to find config file at " + propertyFile + ".  empty caps will be loaded.");
            capFromFile = new Capability();
        }

        // load properties from command line arguments
        capFromCommandLine = new Capability(Constants.cliParameters, Constants.CLI_PARAMETER_PREFIX_WEB);


        // load properties from extra code
        finalCaps.set(capFromFile
                        .addCapability(capFromCommandLine)
                        .addCapability(extraCapFromCode));

        return finalCaps.get();

    }

    public static String getBrowser() {
        // Get parameter from command line
        String propertyFile = System.getProperty(Constants.CLI_PARAM_CONFIG_WEB);
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = Constants.WEB_CONFIG_FILE;
        }

        try {
            return PropertiesLoader.getProperty(propertyFile, Constants.CAPABILITY_BROWSER).toString();
        } catch (PropertiesException e) {
            return Constants.DRIVER_TYPE_CHROME;
        }

    }

    public static String getEnv() {
        // Get parameter from command line
        String propertyFile = System.getProperty(Constants.CLI_PARAM_CONFIG_WEB);
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = Constants.WEB_CONFIG_FILE;
        }

        try {
            return PropertiesLoader.getProperty(propertyFile, Constants.CAPABILITY_ENV).toString();
        } catch (PropertiesException e) {
            return Constants.ENV_LOCAL;
        }
    }

}
