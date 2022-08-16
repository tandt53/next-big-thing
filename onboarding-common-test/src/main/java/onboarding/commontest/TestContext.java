package onboarding.commontest;

import onboarding.commontest.configuration.Configuration;
import onboarding.commontest.configuration.ConfigurationFactory;
import onboarding.commontest.model.TestSuite;

public class TestContext {

    private static TestContext instance;
    private Configuration configuration;
    private TestSuite testSuite;

    private TestContext() {
        configuration = ConfigurationFactory.getConfiguration();
    }

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    private void addConfiguration(Configuration configuration) {
        this.configuration = configuration.add(this.configuration);
    }

    public Configuration getConfiguration(){
        return configuration;
    }

    public TestSuite getTestSuite(){
        return testSuite;
    }

}



