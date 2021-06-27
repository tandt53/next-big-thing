package tandt.mobile.capability;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ui.capability.Capability;
import ui.capability.CapabilityService;

public class MobileCapabilityService implements CapabilityService {

    @Inject
    @Named("mobile.cli-args")
    private Capability cliCapability;

    @Inject
    @Named("mobile.properties")
    private Capability properties;

    private boolean isLoaded = false;

    /**
     * load capability from config file and command line
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
