package tandt.commontest.configuration;

public class ConfigurationFactory {

    public static Configuration getConfiguration() {
        Configuration cliConfiguration = new CliConfiguration();
        cliConfiguration.load();

        Configuration propertiesConfiguration = new PropertiesConfiguration();
        propertiesConfiguration.load();

        propertiesConfiguration.add(cliConfiguration);
        return propertiesConfiguration;
    }

}
