package tandt.mobile.drivermanager;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String DRIVER_TYPE_ANDROID = "android";
    public static final String DRIVER_TYPE_IOS = "ios";
    public static final String DOT = ".";
    public static String CLI_PARAMETER_PREFIX_MOBILE = "mobile";

    // config files
    public static final String CLI_PARAM_CONFIG_MOBILE = "config.mobile"; // Value of this param is the path of mobile.properties file
    public static final String MOBILE_CONFIG_FILE = "configs/mobile.properties";

    // These properties are keys in config file
    // Common appium capability
    public static final String CAPABILITY_SERVER_URL = "server_url";
    public static final String CAPABILITY_APP_PATH = "app";
    public static final String CAPABILITY_AUTOMATION_NAME = "automationName";
    public static final String CAPABILITY_BROWSER_NAME = "browserName";
    public static final String CAPABILITY_PLATFORM_NAME = "platformName";
    public static final String CAPABILITY_PLATFORM_VERSION = "platformVersion";
    public static final String CAPABILITY_DEVICE_NAME = "deviceName";
    public static final String CAPABILITY_DEVICE_UDID = "udid";
    public static final String CAPABILITY_OTHER_APPS = "otherApps";
    public static final String CAPABILITY_NO_RESET = "noReset";
    public static final String CAPABILITY_ = "noReset";
    public static final String CAPABILITY_FULL_RESET = "fullReset";

    // Properties from command line parameter
    public static final List<String> cliCapabilities = Arrays.asList(CAPABILITY_SERVER_URL,
            CAPABILITY_AUTOMATION_NAME,
            CAPABILITY_BROWSER_NAME,
            CAPABILITY_PLATFORM_NAME,
            CAPABILITY_PLATFORM_VERSION,
            CAPABILITY_DEVICE_NAME,
            CAPABILITY_DEVICE_UDID,
            CAPABILITY_APP_PATH);


}