package com.tandt53.automation.web.drivermanager.options;

import com.tandt53.automation.dataprovider.exceptions.PropertiesException;
import com.tandt53.automation.dataprovider.properties.PropertiesLoader;
import com.tandt53.automation.web.drivermanager.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import java.util.HashMap;
import java.util.Map;

import static com.tandt53.automation.web.drivermanager.Constants.capabilities;


public class Caps {

    private static final String DEFAULT_CONFIG_FILE = "configs/web.properties";

    public static Capabilities loadCaps(){
        String propertyFile = System.getProperty("config");
        if(propertyFile ==null || propertyFile.isEmpty()){
            propertyFile = DEFAULT_CONFIG_FILE;
        }
        Map<String, Object> caps;

        try {
            caps = PropertiesLoader.getMap(propertyFile);
        } catch (PropertiesException e) {
            System.out.println("Unable to find config file at " + propertyFile + ". Default empty caps will be loaded.");
            caps = new HashMap<>();
            e.printStackTrace();
        }

        for(String key : capabilities){
            String value = System.getProperty(key);
            if(value != null && !value.isEmpty()){
                caps.put(key, value);
            }
        }

        return new MutableCapabilities(caps);

    }

    public static String getBrowser() throws PropertiesException {
        String propertyFile = System.getProperty("config");
        if(propertyFile ==null || propertyFile.isEmpty()){
            propertyFile = DEFAULT_CONFIG_FILE;
        }

        return PropertiesLoader.getProperty(propertyFile, Constants.CAPABILITY_BROWSER).toString();
    }

    public static String getEnv() throws PropertiesException {
        String propertyFile = System.getProperty("config");
        if(propertyFile ==null || propertyFile.isEmpty()){
            propertyFile = DEFAULT_CONFIG_FILE;
        }
        return PropertiesLoader.getProperty(propertyFile, Constants.CAPABILITY_ENV).toString();
    }

}
