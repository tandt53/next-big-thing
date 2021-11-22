package tandt.web;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import tandt.common.configurations.ContextImpl;
import tandt.common.configurations.capability.Capability;
import tandt.web.capability.CliArgumentsCapability;
import tandt.web.capability.PropertiesFileCapability;
import tandt.web.drivermanager.*;

import static tandt.web.drivermanager.Constants.*;

@Singleton
public class WebModule extends AbstractModule {
    private Capability cli;
    private Capability propertyFile;

    public WebModule() {
        cli = new CliArgumentsCapability();
        propertyFile = new PropertiesFileCapability();
    }

    @Override
    protected void configure() {
        ContextImpl.createInstance().add(propertyFile.load().add(cli.load()));
        bind(PageFactory.class).to(DefaultPageFactory.class);
        bind(DriverManager.class).toProvider(WebDriverProvider.class).in(Scopes.SINGLETON);

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
