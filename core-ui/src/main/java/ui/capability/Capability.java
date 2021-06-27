package ui.capability;

import java.util.HashMap;
import java.util.Map;

public abstract class Capability {
    protected Map<String, Object> caps = new HashMap<>();

    public abstract Capability load();

    public Map<String, Object> getCapabilities() {
        return caps;
    }

    public Capability add(String key, Object value) {
        caps.put(key, value);
        return this;
    }

    public Capability add(Map<String, Object> capability) {
        caps.putAll(capability);
        return this;
    }

    public Capability add(Capability capability) {
        add(capability.getCapabilities());
        return this;
    }

    public Capability remove(String key) {
        caps.remove(key);
        return this;
    }

    public Object get(String key) {
        return caps.get(key);
    }
}
