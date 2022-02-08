package tandt.commontest.configuration;

import tandt.dataprovider.properties.PropertiesLoader;


public class PropertiesConfiguration extends Configuration {
    private final String RUNNER = "runner";
    private final String ENV = "env";
    private final String CONFIG_FILE = "configFile";
    private final String CONFIG_FILE_NAME_FORMAT = "automation-%s-%s.properties";
    private final String DEFAULT_RUNNER = "local";
    private final String DEFAULT_ENV = "uat";

    @Override
    public Configuration load() {
        String runner = getProperty(System.getProperty(RUNNER), DEFAULT_RUNNER);
        String env = getProperty(System.getProperty(ENV), DEFAULT_ENV);
        String configFile = System.getProperty(CONFIG_FILE);
        if (configFile == null || configFile.isEmpty()) {
            caps = PropertiesLoader.getMap(PropertiesConfiguration.class.getResourceAsStream("/" + String.format(CONFIG_FILE_NAME_FORMAT, runner, env)));
        } else {
            caps = PropertiesLoader.getMap(configFile);
        }
        return this;
    }

    private String getProperty(String value, String defaultValue) {
        return value != null ? value : defaultValue;
    }
}
