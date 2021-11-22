package tandt.mobile;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import tandt.common.configurations.ContextImpl;
import tandt.common.configurations.capability.Capability;
import tandt.mobile.capability.CliArgumentsCapability;
import tandt.mobile.capability.PropertiesFileCapability;
import tandt.mobile.drivermanager.AndroidDriverManager;
import tandt.mobile.drivermanager.DriverManager;
import tandt.mobile.drivermanager.DriverProvider;
import tandt.mobile.drivermanager.IosDriverManager;
import tandt.mobile.page.DefaultPageFactory;
import tandt.mobile.page.PageFactory;

@Singleton
public class MobileModule extends AbstractModule {
    private Capability cli;
    private Capability propertyFile;

    public MobileModule() {
        cli = new CliArgumentsCapability();
        propertyFile = new PropertiesFileCapability();
    }

    @Override
    protected void configure() {
        ContextImpl.createInstance().add(propertyFile.load().add(cli.load()));
        bind(PageFactory.class).to(DefaultPageFactory.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).toProvider(DriverProvider.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named("android")).to(AndroidDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named("ios")).to(IosDriverManager.class).in(Scopes.SINGLETON);
    }

}
