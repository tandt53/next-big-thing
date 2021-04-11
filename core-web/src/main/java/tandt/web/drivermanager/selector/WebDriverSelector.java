package tandt.web.drivermanager.selector;

import tandt.web.drivermanager.Constants;
import ui.driverselector.DriverSelector;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverSelector implements DriverSelector {

    private String targetedDriver;

    @Override
    public String get() {
        if (targetedDriver == null)
            loadProperty();
        return targetedDriver;
    }

    private void loadProperty() {
        String browser = System.getProperty(Constants.CLI_PARAMETER_PREFIX_WEB + Constants.CAPABILITY_BROWSER);
        String env = System.getProperty(Constants.CLI_PARAMETER_PREFIX_WEB + Constants.CAPABILITY_ENV);
        if (browser != null) {
            targetedDriver = ((env == null) ? Constants.ENV_LOCAL : env) + "." + browser;
        } else {
            String propertyFile = System.getProperty("config.web");
            if (propertyFile == null || propertyFile.isEmpty()) {
                propertyFile = Constants.WEB_CONFIG_FILE;
            }
            Properties properties = new Properties();
            try (FileInputStream fileInputStream = new FileInputStream(propertyFile)) {
                properties.load(fileInputStream);
                targetedDriver = properties.getProperty(Constants.CAPABILITY_ENV) + "." + properties.getProperty(Constants.CAPABILITY_BROWSER);
            } catch (IOException e) {
                targetedDriver = Constants.ENV_LOCAL + "." + Constants.DRIVER_TYPE_CHROME;
            }
        }
    }
}
