package onboarding.commontest.configuration;

import java.util.Properties;
import java.util.Set;

public class CliConfiguration extends Configuration {
    private String prefix = "onboarding.";

    @Override
    public Configuration load() {
        Properties properties = System.getProperties();
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            if (key.startsWith(prefix)) {
                caps.put(key, properties.getProperty(key));
            }
        }
        return this;
    }
}
