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
    public void add(String key, Object value) {
        capability.add(key, value);
    }

    @Override
    public void add(Capability capability) {
        this.capability.add(capability);
    }

    @Override
    public void remove(String key) {
        this.capability.remove(key);
    }

    @Override
    public void removeAll() {
        this.capability.removeAll();
    }
}
