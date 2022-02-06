package tandt.commontest.configuration.manager;

import tandt.commontest.configuration.Configuration;


public class ConfigurationManagerFactory {

    private static String runner = System.getProperty("runner");
    private static String env = System.getProperty("env");
    private static String configFile = System.getProperty("configFile") != null ? System.getProperty("configFile") : String.format("config-%s-%s.properties", runner, env);

    public static Configuration getConfigurationManager() {
        Configuration propertiesConfiguration = new PropertiesConfiguration();
        Configuration cliConfiguration = new CliConfiguration();

        propertiesConfiguration.load();
        cliConfiguration.load();

        propertiesConfiguration.add(cliConfiguration);
        return propertiesConfiguration;
    }
}
