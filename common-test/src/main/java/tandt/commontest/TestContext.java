package tandt.commontest;

import tandt.commontest.configuration.Configuration;
import tandt.commontest.configuration.ConfigurationFactory;

public class TestContext {

    private static TestContext instance;

    private TestContext() {
        configuration = ConfigurationFactory.getConfiguration();
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

    public Configuration getConfiguration(){
        return configuration;
    }

//    private TestScenarioManager testScenarioManager;

}
