package tandt.web.test.test;


import tandt.common.configurations.capability.Capability;

public class ExtraCaps extends Capability {

    @Override
    public Capability load() {
        caps.put("app", "com.android.chrome");
        return this;
    }
}
