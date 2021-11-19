package tandt.web;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import tandt.web.capability.CliArgumentsCapability;
import tandt.web.capability.PropertiesFileCapability;
import tandt.web.capability.WebCapabilityService;
import tandt.web.drivermanager.*;
import tandt.web.drivermanager.selector.WebDriverSelector;
import tandt.common.configurations.capability.Capability;
import tandt.common.configurations.capability.CapabilityService;
import ui.driverselector.DriverSelector;

import static tandt.web.drivermanager.Constants.*;

@Singleton
public class WebModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Capability.class).annotatedWith(Names.named("web.cli-args")).to(CliArgumentsCapability.class).in(Scopes.SINGLETON);
        bind(Capability.class).annotatedWith(Names.named("web.properties")).to(PropertiesFileCapability.class).in(Scopes.SINGLETON);
        bind(CapabilityService.class).annotatedWith(Names.named("web")).to(WebCapabilityService.class).in(Scopes.SINGLETON);
        bind(PageFactory.class).to(DefaultPageFactory.class);
        bind(DriverManager.class).toProvider(WebDriverProvider.class).in(Scopes.SINGLETON);
        bind(DriverSelector.class).annotatedWith(Names.named("web")).to(WebDriverSelector.class).in(Scopes.SINGLETON);

        bind(DriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_CHROME)).to(ChromeDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_FIREFOX)).to(FirefoxDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_SAFARI)).to(SafariDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_EDGE)).to(ChromeDriverManager.class).in(Scopes.SINGLETON);

        bind(DriverManager.class).annotatedWith(Names.named(ENV_LOCAL + DOT + DRIVER_TYPE_CHROME)).to(ChromeDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_LOCAL + DOT + DRIVER_TYPE_FIREFOX)).to(FirefoxDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_LOCAL + DOT + DRIVER_TYPE_SAFARI)).to(SafariDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_LOCAL + DOT + DRIVER_TYPE_EDGE)).to(ChromeDriverManager.class).in(Scopes.SINGLETON);

        bind(DriverManager.class).annotatedWith(Names.named(ENV_REMOTE + DOT + DRIVER_TYPE_CHROME)).to(RemoteDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_REMOTE + DOT + DRIVER_TYPE_FIREFOX)).to(RemoteDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_REMOTE + DOT + DRIVER_TYPE_SAFARI)).to(RemoteDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_REMOTE + DOT + DRIVER_TYPE_EDGE)).to(RemoteDriverManager.class).in(Scopes.SINGLETON);


    }

}
