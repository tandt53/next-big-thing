package tandt.mobile.capability;

import tandt.mobile.drivermanager.Constants;
import tandt.common.configurations.capability.Capability;

import java.util.List;

public class CliArgumentsCapability extends Capability {
    private String prefix = Constants.CLI_PARAMETER_PREFIX_MOBILE;
    private List<String> listArgs = Constants.cliParameters;

    @Override
    public Capability load() {
        for (String key : listArgs) {
            String value = System.getProperty(prefix + key);
            if (value != null && !value.isEmpty()) {
                caps.put(key, value);
            }
        }
        return this;
    }

}
