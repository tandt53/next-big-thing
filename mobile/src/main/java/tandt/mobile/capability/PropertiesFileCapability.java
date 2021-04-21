package tandt.mobile.capability;

import tandt.dataprovider.properties.PropertiesLoader;
import tandt.mobile.drivermanager.Constants;
import ui.capability.Capability;


public class PropertiesFileCapability extends Capability {

    @Override
    public Capability load() {
        String propertyFile = System.getProperty("config.web");
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = Constants.MOBILE_CONFIG_FILE;
        }
        caps = PropertiesLoader.getMap(propertyFile);
        return this;
    }

}
