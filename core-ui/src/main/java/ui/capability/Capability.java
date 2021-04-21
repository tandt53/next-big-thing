package ui.capability;

import java.util.HashMap;
import java.util.Map;

public abstract class Capability {
    protected Map<String, String> caps = new HashMap<>();

    public abstract Capability load();

    public Map<String, String> getCapabilities() {
        return caps;
    }

    public Capability add(String key, String value) {
        caps.put(key, value);
        return this;
    }

    public Capability add(Map<String, String> capability) {
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

    public  String get(String key){
        return caps.get(key);
    }
}
