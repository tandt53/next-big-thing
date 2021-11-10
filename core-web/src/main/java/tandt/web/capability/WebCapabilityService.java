package tandt.web.capability;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ui.capability.Capability;
import ui.capability.CapabilityService;

public class WebCapabilityService implements CapabilityService {

    @Inject
    @Named("web.cli-args")
    private Capability cliCapability;

    @Inject
    @Named("web.properties")
    private Capability properties;

    private boolean isLoaded = false;

    /**
     * load capability from config file or command line
     */
    private void loadCapabilities() {
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
