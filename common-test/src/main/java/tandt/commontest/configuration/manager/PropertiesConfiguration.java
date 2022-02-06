package tandt.commontest.configuration.manager;

import tandt.commontest.configuration.Configuration;

public class PropertiesConfiguration extends Configuration {
    @Override
    public Configuration load() {
        System.out.println("Loading properties configuration");
        return this;
    }
}
