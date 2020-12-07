package com.tandt53.automation.web.drivermanager;

import com.tandt53.automation.dataprovider.exceptions.PropertiesException;
import com.tandt53.automation.dataprovider.properties.PropertiesLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Capability {
    private Map<String, Object> map;

    public Capability() {
        map = new HashMap<>();
    }

    public Capability(String propertiesFile) throws PropertiesException {
        map = PropertiesLoader.getMap(propertiesFile);
    }

    public Capability(List<String> listOfPropertiesKeys) {
        map = new HashMap<>();
        for (String key : listOfPropertiesKeys) {
            String value = System.getProperty(key);
            if (value != null && !value.isEmpty()) {
                map.put(key, value);
            }
        }
    }

    public Capability(List<String> listOfPropertiesKeys, String prefix) {
        map = new HashMap<>();
        for (String key : listOfPropertiesKeys) {
            String value = System.getProperty(key);
            if (value != null && !value.isEmpty() && key.startsWith(prefix)) {
                map.put(key.substring(prefix.length()), value);
            }
        }
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

    public Object getValue(String key) {
        return map.get(key);
    }

    public Capability addMap(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public Capability removeInfo(String key) {
        map.remove(key);
        return this;
    }

    public Capability addMap(Map<String, Object> map) {
        this.map.putAll(map);
        return this;
    }

    public Capability addCapability(Capability capability) {
        if (capability != null)
            return this.addMap(capability.getMap());
        return this;
    }
}
