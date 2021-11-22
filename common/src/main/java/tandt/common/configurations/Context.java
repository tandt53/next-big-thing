package tandt.common.configurations;

import tandt.common.configurations.capability.Capability;

public interface Context {

    Capability getCapability();

    void add(String key, Object value);

    void add(Capability capability);

    void remove(String key);

    void removeAll();

}
