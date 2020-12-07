package com.tandt53.automation.web.drivermanager.options;

import com.tandt53.automation.dataprovider.exceptions.PropertiesException;
import com.tandt53.automation.dataprovider.properties.PropertiesLoader;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;

import static com.tandt53.automation.web.drivermanager.Constants.*;


public class CapabilityManager {

    private static ThreadLocal<Capability> inputCaps = new ThreadLocal<>();
    private static ThreadLocal<Capability> finalCaps = new ThreadLocal<>();

    public static void add(Capability cap) {
        inputCaps.get().addMap(cap.getMap());
    }

    public static Capability getInputCapabilities() {
        return inputCaps.get();
    }

    public static Capability loadCaps() {

        String propertyFile = System.getProperty("config.mobile");
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = DEFAULT_CONFIG_FILE;
        }

        Capability capFromFile;
        Capability capFromCommandLine;
        Capability capFromExtraCode = inputCaps.get();
        // load properties from file
        try {
            capFromFile = new Capability(propertyFile);
        } catch (PropertiesException e) {
            System.out.println("Unable to find config file at " + propertyFile + ". Default empty caps will be loaded.");
            capFromFile = new Capability();
        }

        // load properties from command line arguments
        capFromCommandLine = new Capability(cliParameters, CLI_PARAMETER_PREFIX_WEB);


        // load properties from extra code
        finalCaps.set(capFromFile.addCapability(capFromCommandLine)
                .addCapability(capFromExtraCode));

        return finalCaps.get();

    }

    public static String getBrowser()  {
        // Get parameter from command line
        String propertyFile = System.getProperty(CLI_PARAM_CONFIG_WEB);
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = DEFAULT_CONFIG_FILE;
        }

        try {
            return PropertiesLoader.getProperty(propertyFile, CAPABILITY_BROWSER).toString();
        } catch (PropertiesException e) {
            return DRIVER_TYPE_CHROME;
        }

    }

    public static String getEnv() {
        String propertyFile = System.getProperty(CLI_PARAM_CONFIG_WEB);
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = DEFAULT_CONFIG_FILE;
        }
        try {
            return PropertiesLoader.getProperty(propertyFile, CAPABILITY_ENV).toString();
        } catch (PropertiesException e) {
            return ENV_LOCAL;
        }
    }

}
