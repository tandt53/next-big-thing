package tandt.commontest;

import tandt.commontest.configuration.Configuration;
import tandt.commontest.configuration.manager.ConfigurationManagerFactory;

public class TestContext {

    private static TestContext instance;

    private TestContext() {
        configuration = ConfigurationManagerFactory.getConfigurationManager();
    }

    public static TestContext getInstance() {

        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    private Configuration configuration;

    private void addConfiguration(Configuration configuration) {
        this.configuration = configuration.add(this.configuration);
    }

//    private TestScenarioManager testScenarioManager;

}
