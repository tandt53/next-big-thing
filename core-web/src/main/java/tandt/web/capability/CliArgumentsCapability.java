package tandt.web.capability;

import tandt.common.configurations.capability.Capability;
import tandt.web.drivermanager.Constants;

import java.util.List;
import java.util.Properties;
import java.util.Set;

public class CliArgumentsCapability extends Capability {
    private String prefix = Constants.CLI_PARAMETER_PREFIX_WEB;
    private List<String> listArgs = Constants.cliParameters;

    @Override
    public Capability load() {
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
