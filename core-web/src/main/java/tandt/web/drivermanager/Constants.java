package tandt.web.drivermanager;

import java.util.Arrays;
import java.util.List;

public class Constants {

    Constants() {
    }

    public static final String DRIVER_TYPE_CHROME = "chrome";
    public static final String DRIVER_TYPE_FIREFOX = "firefox";
    public static final String DRIVER_TYPE_SAFARI = "safari";
    public static final String DRIVER_TYPE_EDGE = "edge";
    public static final String ENV_LOCAL = "local";
    public static final String ENV_REMOTE = "remote";
    public static final String DOT = ".";


    public static final String WEB_CONFIG_FILE = "configs/web.properties";
    public static final String CLI_PARAM_CONFIG_WEB = "config.web";



    /**
     * All capabilities in command line arguments must have prefix "web".
     * It avoids duplicate with mobile capabilities. Ex: browser, browser_version, etc.
     */
    public static final String CLI_PARAMETER_PREFIX_WEB = "web";
    // capabilities
    public static final String CAPABILITY_ENV = "env";
    public static final String CAPABILITY_OS = "os";
    public static final String CAPABILITY_OS_VERSION = "os_version";
    public static final String CAPABILITY_REMOTE_HOST = "remote_url";
    public static final String CAPABILITY_BROWSER = "browser";
    public static final String CAPABILITY_BROWSER_VERSION = "browser_version";
    public static final String CAPABILITY_DRIVER_PATH = "driver_path";
    public static final String CAPABILITY_ARGS = "args";
    public static final String CAPABILITY_BINARY = "binary";
    public static final String CAPABILITY_EXTENSIONS = "extensions";
    public static final String CAPABILITY_LOCAL_STATE = "localState";
    public static final String CAPABILITY_PREFS = "prefs";
    public static final String CAPABILITY_DETACH = "detach";
    public static final String CAPABILITY_DEBUGGER_ADDRESS = "debuggerAddress";
    public static final String CAPABILITY_EXCLUDE_SWITCHES = "excludeSwitches";
    public static final String CAPABILITY_MINIDUMP_PATH = "minidumpPath";
    public static final String CAPABILITY_MOBILE_EMULATOR = "mobileEmulation";
    public static final String CAPABILITY_PERF_LOGGING_PREFS = "perfLoggingPrefs";
    public static final String CAPABILITY_WINDOW_TYPES = "windowTypes";

    public static final List<String> cliParameters = Arrays.asList(CAPABILITY_ENV,
            CAPABILITY_OS,
            CAPABILITY_OS_VERSION,
            CAPABILITY_BROWSER,
            CAPABILITY_BROWSER_VERSION,
            CAPABILITY_DRIVER_PATH,
            CAPABILITY_ARGS,
            CAPABILITY_BINARY,
            CAPABILITY_EXTENSIONS,
            CAPABILITY_LOCAL_STATE,
            CAPABILITY_PREFS,
            CAPABILITY_DETACH,
            CAPABILITY_DEBUGGER_ADDRESS,
            CAPABILITY_EXCLUDE_SWITCHES,
            CAPABILITY_MINIDUMP_PATH,
            CAPABILITY_MOBILE_EMULATOR,
            CAPABILITY_PERF_LOGGING_PREFS,
            CAPABILITY_WINDOW_TYPES);




}
