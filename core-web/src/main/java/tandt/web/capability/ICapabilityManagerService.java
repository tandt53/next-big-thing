package tandt.web.capability;

public interface ICapabilityManagerService {

    void loadCapabilities();

    ICapability getCapability();

    void addCapabilities(ICapability capability);

}
