package tandt.common.configurations;

import tandt.common.configurations.capability.Capability;

public class ContextImpl implements Context {
    private static Context context;

    private Capability capability;

    private ContextImpl() {
        capability = new Capability() {
            @Override
            public Capability load() {
                return this;
            }
        };
    }

    public static Context createInstance() {
        if (context == null)
            context = new ContextImpl();
        return context;
    }

    @Override
    public Capability getCapability() {
        return this.capability;
    }

    @Override
    public Context addValue(String key, Object value) {
        capability.add(key, value);
        return this;
    }

    @Override
    public Context addValue(Capability capability) {
        this.capability.add(capability);
        return this;
    }

    @Override
    public Context remove(String key) {
        this.capability.remove(key);
        return this;
    }

    @Override
    public Context removeAll() {
        this.capability.removeAll();
        return this;
    }

}
