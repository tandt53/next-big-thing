package tandt.common.configurations;

import tandt.common.configurations.capability.Capability;


public interface Context {

    Capability getCapability();

    Context add(String key, Object value);

    Context add(Capability capability);

    Context remove(String key);

    Context removeAll();


}
