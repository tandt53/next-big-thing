package tandt.web;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import org.openqa.selenium.WebDriver;
import tandt.web.capability.DefaultCapability;
import tandt.web.capability.WebCapabilityService;
import tandt.web.drivermanager.*;
import ui.capability.Capability;
import ui.capability.CapabilityService;

import static tandt.web.drivermanager.Constants.*;

@Singleton
public class WebModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(Capability.class).to(DefaultCapability.class).in(Scopes.SINGLETON);
        bind(PageFactory.class).to(DefaultPageFactory.class);
        bind(CapabilityService.class).to(WebCapabilityService.class);
        bind(WebDriver.class).toProvider(WebDriverProvider.class).in(Scopes.SINGLETON);

        bind(DriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_CHROME)).to(ChromeDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_FIREFOX)).to(FirefoxDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_SAFARI)).to(SafariDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_EDGE)).to(ChromeDriverManager.class).in(Scopes.SINGLETON);

        bind(DriverManager.class).annotatedWith(Names.named(ENV_LOCAL + DOT + DRIVER_TYPE_CHROME)).to(ChromeDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_LOCAL + DOT + DRIVER_TYPE_FIREFOX)).to(FirefoxDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_LOCAL + DOT + DRIVER_TYPE_SAFARI)).to(SafariDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_LOCAL + DOT + DRIVER_TYPE_EDGE)).to(ChromeDriverManager.class);

        bind(DriverManager.class).annotatedWith(Names.named(ENV_REMOTE + DOT + DRIVER_TYPE_CHROME)).to(RemoteDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_REMOTE + DOT + DRIVER_TYPE_FIREFOX)).to(RemoteDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_REMOTE + DOT + DRIVER_TYPE_SAFARI)).to(RemoteDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_REMOTE + DOT + DRIVER_TYPE_EDGE)).to(RemoteDriverManager.class);
    }

}
