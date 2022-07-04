package onboarding.commontest.configuration;

public class ConfigurationFactory {

    public static Configuration getConfiguration() {
        System.out.println("get configuration");
        Configuration cliConfiguration = new CliConfiguration();
        cliConfiguration.load();

        Configuration propertiesConfiguration = new PropertiesConfiguration();
        propertiesConfiguration.load();

        propertiesConfiguration.add(cliConfiguration);
        return propertiesConfiguration;
    }

}
