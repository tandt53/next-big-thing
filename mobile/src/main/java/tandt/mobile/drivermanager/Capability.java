package tandt.mobile.drivermanager;

import tandt.dataprovider.exceptions.PropertiesException;
import tandt.dataprovider.properties.PropertiesLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Capability {
    private Map<String, String> map;

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

    public Map<String, String> getMap() {
        return this.map;
    }

    public String getValue(String key) {
        return map.get(key);
    }

    public Capability addMap(String key, String value) {
        map.put(key, value);
        return this;
    }

    public Capability removeInfo(String key) {
        map.remove(key);
        return this;
    }

    public Capability addMap(Map<String, String> map) {
        this.map.putAll(map);
        return this;
    }

    public Capability addCapability(Capability capability) {
        if (capability != null)
            return this.addMap(capability.getMap());
        return this;
    }
}
