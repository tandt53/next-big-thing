package tandt.web.capability;

import com.google.inject.Inject;
import tandt.dataprovider.exceptions.PropertiesException;
import tandt.web.drivermanager.Constants;

public class CapabilityManagerService implements ICapabilityManagerService{

    @Inject
    ICapability capability;

    /**
     * load capability from config file or command line
     */
    public void loadCapabilities(){
        String propertyFile = System.getProperty("config.web");
        if (propertyFile == null || propertyFile.isEmpty()) {
            propertyFile = Constants.WEB_CONFIG_FILE;
        }

        ICapability capFromCommandLine;
        // load properties from file
        try {
            capability.addCapability(propertyFile);
        } catch (PropertiesException e) {
            System.out.println("Unable to find config file at " + propertyFile + ".  empty caps will be loaded.");
            capability = new Capability();
        }

        // load properties from command line arguments
        capability.addCapability(Constants.cliParameters, Constants.CLI_PARAMETER_PREFIX_WEB);

    }

    @Override
    public ICapability getCapability() {
        return capability;
    }

    @Override
    public void addCapabilities(ICapability capability) {
        this.capability.addCapability(capability);
    }


}
