package tandt.web.capability;

import com.google.inject.Inject;
import tandt.dataprovider.exceptions.PropertiesException;
import tandt.web.drivermanager.Constants;

public class DefaultCapabilityService implements CapabilityService {

    @Inject
    Capability capability;

    /**
     * load capability from config file or command line
     */
    public void loadCapabilities(){
        String propertyFile = System.getProperty("config.web");
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = Constants.WEB_CONFIG_FILE;
        }

        // load properties from file
        try {
            capability.addCapability(propertyFile);
        } catch (PropertiesException e) {
            System.out.println("Unable to find config file at " + propertyFile + ".  empty caps will be loaded.");
            capability = new DefaultCapability();
        }

        // load properties from command line arguments
        capability.addCapability(Constants.cliParameters, Constants.CLI_PARAMETER_PREFIX_WEB);
    }

    @Override
    public Capability getCapability() {
        return capability;
    }

    @Override
    public void addCapabilities(Capability capability) {
        this.capability.addCapability(capability);
    }


}
