package light.commontest;

import light.commontest.configuration.Configuration;
import light.commontest.configuration.ConfigurationFactory;
import light.commontest.model.TestSuite;

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



