package tandt.web.capability;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import tandt.common.configurations.capability.Capability;
import tandt.common.configurations.capability.CapabilityService;

public class WebCapabilityService implements CapabilityService {
    private static CapabilityService service;

    @Inject
    @Named("web.cli-args")
    private Capability cliCapability;

    @Inject
    @Named("web.properties")
    private Capability properties;

    private boolean isLoaded = false;

//    private WebCapabilityService() {
//    }
//
//    public static CapabilityService getInstance(){
//        if (service == null) {
//            service = new WebCapabilityService();
//        }
//        return service;
//    }


    /**
     * load capability from config file or command line
     */
    private void loadCapabilities() {
        if (properties == null) {
            properties = new PropertiesFileCapability();
        }
        if (cliCapability == null) {
            cliCapability = new CliArgumentsCapability();
        }
        cliCapability.load();
        properties.load().add(cliCapability);
        isLoaded = true;
    }

    @Override
    public Capability getCapability() {
        if (!isLoaded) {
            loadCapabilities();
        }
        return properties;
    }

    @Override
    public void addCapability(Capability capability) {
        this.properties.add(capability);
    }


}
