//package tandt.mobile.capability;
//
//import tandt.common.Utils;
//import tandt.common.configurations.capability.Capability;
//import tandt.mobile.drivermanager.Constants;
//
//import java.util.List;
//import java.util.Properties;
//import java.util.Set;
//
//public class CliArgumentsCapability extends Capability {
//    private String prefix = Constants.CLI_PARAMETER_PREFIX_MOBILE;
//    private List<String> listArgs = Constants.cliParameters;
//
//    @Override
//    public Capability load() {
//        Properties properties = System.getProperties();
//        Set<String> keys = properties.stringPropertyNames();
//        for (String key : keys) {
//            if (key.startsWith(prefix)) {
//                String value = properties.getProperty(key);
//                if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
//                    caps.put(key.replaceFirst(prefix, ""), Boolean.parseBoolean(value));
//                } else {
//                    caps.put(key.replaceFirst(prefix, ""), Utils.parseVariables(properties.getProperty(key)));
//                }
//            }
//        }
//        return this;
//    }
//
//}
