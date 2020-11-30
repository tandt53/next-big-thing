package com.tandt53.automation.web.drivermanager.options;

import com.tandt53.automation.dataprovider.exceptions.PropertiesException;
import com.tandt53.automation.dataprovider.properties.PropertiesLoader;
import com.tandt53.automation.web.drivermanager.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tandt53.automation.web.drivermanager.Constants.capabilities;


public class Caps {

    private static final String DEFAULT_CONFIG_FILE = "configs/browser.properties";
    private String propertyFile;
    private Map<String, Object> caps;

    public Caps() {
        this.propertyFile = DEFAULT_CONFIG_FILE;
        load();
    }

    public Caps(String propertyFile) {
        this.propertyFile = propertyFile;
        load();
    }

    public void load() {
        try {
            caps = PropertiesLoader.getMap(this.propertyFile);
        } catch (PropertiesException e) {
            System.out.println("Unable to find config file at " + this.propertyFile + ". Default empty caps will be loaded.");
            caps = new HashMap<>();
            e.printStackTrace();
        }
    }

    public Object getCapability(String key) {
        return caps.get(key);
    }

    public void setCapability(String key, String value) {
        caps.put(key, value);
    }


    public Capabilities getCapabilities() {
        for(String key : capabilities){
            String value = System.getProperty(key);
            if(value != null && !value.isEmpty()){
                caps.put(key, value);
            }
        }

        return new MutableCapabilities(caps);
    }


}
