package tandt.mobile;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import tandt.common.configurations.Context;
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

import java.util.Map;

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
        Context context = ContextImpl.createInstance().addValue(propertyFile.load().add(cli.load()));
        bindContext(context);
        bind(PageFactory.class).to(DefaultPageFactory.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).toProvider(DriverProvider.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named("android")).to(AndroidDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named("ios")).to(IosDriverManager.class).in(Scopes.SINGLETON);
    }

    private void bindContext(Context context) {
        Map<String, Object> capabilities = context.getCapability().getCapabilities();
        for (Map.Entry<String, Object> entry : capabilities.entrySet()) {
            bind(String.class).annotatedWith(Names.named(entry.getKey())).toInstance(String.valueOf(entry.getValue()));
        }
    }

}
