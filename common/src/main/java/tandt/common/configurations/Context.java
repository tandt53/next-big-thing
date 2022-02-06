package tandt.common.configurations;

import tandt.common.configurations.capability.Capability;


public interface Context {

    Capability getCapability();

    Context addValue(String key, Object value);

    Context addValue(Capability capability);

    Context remove(String key);

    Context removeAll();


}
