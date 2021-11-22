package tandt.mobile.capability;

import tandt.common.configurations.capability.Capability;
import tandt.dataprovider.properties.PropertiesLoader;

import static tandt.mobile.drivermanager.Constants.MOBILE_CONFIG_FILE;


public class PropertiesFileCapability extends Capability {

    @Override
    public Capability load() {
        String propertyFile = System.getProperty("config.mobile");
        if (propertyFile == null || propertyFile.isEmpty()) {
            caps = PropertiesLoader.getMap(PropertiesFileCapability.class
                    .getResourceAsStream("/" + MOBILE_CONFIG_FILE));
        } else {
            caps = PropertiesLoader.getMap(propertyFile);
        }
        return this;
    }

}
