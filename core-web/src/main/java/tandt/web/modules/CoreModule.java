package tandt.web.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import tandt.web.capability.CapabilityManagerService;
import tandt.web.capability.ICapability;
import tandt.web.capability.Capability;
import tandt.web.capability.ICapabilityManagerService;
import tandt.web.drivermanager.*;
import tandt.web.example.IDriverInit;
import tandt.web.example.MainPage;

import static tandt.web.drivermanager.Constants.*;

public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IDriverInit.class).to(MainPage.class).in(Scopes.SINGLETON);
        bind(ICapability.class).to(Capability.class).in(Scopes.SINGLETON);
        bind(ICapabilityManagerService.class).to(CapabilityManagerService.class).in(Scopes.SINGLETON);

        bind(DriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_CHROME)).to(ChromeDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_FIREFOX)).to(FirefoxDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_SAFARI)).to(SafariDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_EDGE)).to(ChromeDriverManager.class).in(Scopes.SINGLETON);


        bind(DriverManager.class).annotatedWith(Names.named(ENV_LOCAL + DOT + DRIVER_TYPE_CHROME)).to(ChromeDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_LOCAL + DOT + DRIVER_TYPE_FIREFOX)).to(FirefoxDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_LOCAL + DOT + DRIVER_TYPE_SAFARI)).to(SafariDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_LOCAL + DOT + DRIVER_TYPE_EDGE)).to(ChromeDriverManager.class);

        bind(DriverManager.class).annotatedWith(Names.named(ENV_REMOTE + DOT + DRIVER_TYPE_CHROME)).to(RemoteDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_REMOTE + DOT + DRIVER_TYPE_FIREFOX)).to(RemoteDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_REMOTE + DOT + DRIVER_TYPE_SAFARI)).to(RemoteDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(ENV_REMOTE + DOT + DRIVER_TYPE_EDGE)).to(RemoteDriverManager.class);
    }
}
