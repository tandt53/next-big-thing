package tandt.web.capability;

import tandt.dataprovider.properties.PropertiesLoader;
import tandt.common.configurations.capability.Capability;

import static tandt.web.drivermanager.Constants.WEB_CONFIG_FILE;


public class PropertiesFileCapability extends Capability {

    @Override
    public Capability load() {
        String propertyFile = System.getProperty("config.web");
        if (propertyFile == null || propertyFile.isEmpty()) {
            caps = PropertiesLoader.getMap(PropertiesFileCapability.class
                    .getResourceAsStream("/" + WEB_CONFIG_FILE));
        } else {
            caps = PropertiesLoader.getMap(propertyFile);
        }
        return this;


    }
}