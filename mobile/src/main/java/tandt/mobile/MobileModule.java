package tandt.mobile;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import io.appium.java_client.MobileDriver;
import tandt.mobile.capability.CliArgumentsCapability;
import tandt.mobile.capability.MobileCapabilityService;
import tandt.mobile.capability.PropertiesFileCapability;
import tandt.mobile.drivermanager.*;
import tandt.mobile.drivermanager.selector.MobileDriverSelector;
import tandt.mobile.page.DefaultPageFactory;
import tandt.mobile.page.PageFactory;
import tandt.common.configurations.capability.Capability;
import tandt.common.configurations.capability.CapabilityService;
import ui.driverselector.DriverSelector;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Singleton
public class MobileModule extends AbstractModule {

    @Override
    protected void configure() {
        String platform = getProperties();
        bind(String.class).annotatedWith(Names.named(Constants.CAPABILITY_PLATFORM_NAME)).toInstance(platform);

        bind(Capability.class).annotatedWith(Names.named("mobile.cli-args")).to(CliArgumentsCapability.class).in(Scopes.SINGLETON);
        bind(Capability.class).annotatedWith(Names.named("mobile.properties")).to(PropertiesFileCapability.class).in(Scopes.SINGLETON);

        bind(CapabilityService.class).annotatedWith(Names.named("mobile")).to(MobileCapabilityService.class).in(Scopes.SINGLETON);
        bind(DriverSelector.class).annotatedWith(Names.named("mobile")).to(MobileDriverSelector.class);
        bind(PageFactory.class).to(DefaultPageFactory.class).in(Scopes.SINGLETON);
        bind(MobileDriver.class).toProvider(DriverProvider.class).in(Scopes.SINGLETON);

        if (platform.equalsIgnoreCase(Constants.DRIVER_TYPE_ANDROID))
            bind(DriverManager.class).to(AndroidDriverManager.class).in(Scopes.SINGLETON);
        else if (platform.equalsIgnoreCase(Constants.DRIVER_TYPE_IOS))
            bind(DriverManager.class).to(IosDriverManager.class).in(Scopes.SINGLETON);
    }

    private String getProperties() {

        // get from command line
        String platform = System.getProperty(Constants.CAPABILITY_PLATFORM_NAME);
        if (platform == null) {
            String configFile = System.getProperty(Constants.CLI_PARAMETER_PREFIX_MOBILE + Constants.CAPABILITY_PLATFORM_NAME);
            if (configFile == null) {
                configFile = Constants.MOBILE_CONFIG_FILE;
            }
            Properties properties = new Properties();
            try (FileInputStream fileInputStream = new FileInputStream(configFile)) {
                properties.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            platform = properties.getProperty(Constants.CAPABILITY_PLATFORM_NAME);
        }

        return platform;

    }

}
