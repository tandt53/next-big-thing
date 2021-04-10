package tandt.web.capability;

import com.google.inject.Inject;
import tandt.dataprovider.exceptions.PropertiesException;
import tandt.web.drivermanager.Constants;
import ui.capability.Capability;
import ui.capability.CapabilityService;

public class WebCapabilityService implements CapabilityService {

    @Inject
    Capability capability;

    /**
     * load capability from config file or command line
     */
    private void loadCapabilities() {
        String propertyFile = System.getProperty("config.web");
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = Constants.WEB_CONFIG_FILE;
        }

        // load properties from file
        try {
            capability.add(propertyFile);
        } catch (PropertiesException e) {
            System.out.println("Unable to find config file at " + propertyFile + ".  empty caps will be loaded.");
            capability = new DefaultCapability();
        }

        // load properties from command line arguments
        capability.add(Constants.cliParameters, Constants.CLI_PARAMETER_PREFIX_WEB);
    }

    @Override
    public Capability getCapability() {
        if (capability.getCapabilities().isEmpty()) {
            loadCapabilities();
        }
        return capability;
    }

    @Override
    public void addCapability(Capability capability) {
        this.capability.add(capability);
    }


}
