package tandt.web.capability;

import tandt.dataprovider.properties.PropertiesLoader;
import tandt.web.drivermanager.Constants;
import ui.capability.Capability;


public class PropertiesFileCapability extends Capability {

    @Override
    public Capability load() {
        String propertyFile = System.getProperty("config.web");
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = Constants.WEB_CONFIG_FILE;
        }
        caps = PropertiesLoader.getMap(propertyFile);
        return this;
    }


}
