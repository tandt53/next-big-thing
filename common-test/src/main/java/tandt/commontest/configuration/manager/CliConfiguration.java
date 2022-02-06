package tandt.commontest.configuration.manager;

import tandt.commontest.configuration.Configuration;

public class CliConfiguration extends Configuration {

    @Override
    public Configuration load() {
        System.out.println("Load XML configuration");
        return this;
    }
}
