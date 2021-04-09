package tandt.web.capability;

import tandt.dataprovider.exceptions.PropertiesException;
import tandt.dataprovider.properties.PropertiesLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultCapability implements Capability {
    private Map<String, String> map;

    public DefaultCapability() {
        map = new HashMap<>();
    }

    public Capability addCapability(String propertiesFile) throws PropertiesException {
        map = PropertiesLoader.getMap(propertiesFile);
        return this;
    }

    public Capability addCapability(List<String> listOfPropertiesKeys) {

        for (String key : listOfPropertiesKeys) {
            String value = System.getProperty(key);
            if (value != null && !value.isEmpty()) {
                map.put(key, value);
            }
        }
        return this;
    }

    public Capability addCapability(List<String> listOfPropertiesKeys, String prefix) {

        for (String key : listOfPropertiesKeys) {
            String value = System.getProperty(key);
            if (value != null && !value.isEmpty() && key.startsWith(prefix)) {
                map.put(key.substring(prefix.length()), value);
            }
        }
        return this;
    }

    @Override
    public Map<String, String> getCapabilities() {
        return this.map;
    }

    @Override
    public String getValue(String key) {
        return map.get(key);
    }

    @Override
    public Capability addCapability(String key, String value) {
        map.put(key, value);
        return this;
    }

    @Override
    public Capability remove(String key) {
        map.remove(key);
        return this;
    }

    @Override
    public Capability addCapability(Map<String, String> map) {
        this.map.putAll(map);
        return this;
    }

    @Override
    public Capability addCapability(Capability capability) {
        if (capability != null)
            return this.addCapability(capability.getCapabilities());
        return this;
    }
}
