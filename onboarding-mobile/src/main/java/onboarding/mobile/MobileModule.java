package onboarding.mobile;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import onboarding.mobile.drivermanager.AndroidDriverManager;
import onboarding.mobile.drivermanager.DriverManager;
import onboarding.mobile.drivermanager.DriverProvider;
import onboarding.mobile.drivermanager.IosDriverManager;
import onboarding.mobile.drivermanager.option.AppiumDriverOptionFilter;
import onboarding.mobile.drivermanager.option.DriverOptionFilter;
import onboarding.mobile.page.DefaultPageFactory;
import onboarding.mobile.page.PageFactory;


@Singleton
public class MobileModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DriverOptionFilter.class).to(AppiumDriverOptionFilter.class);
        bind(PageFactory.class).to(DefaultPageFactory.class);
        bind(DriverManager.class).toProvider(DriverProvider.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named("android")).to(AndroidDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named("ios")).to(IosDriverManager.class).in(Scopes.SINGLETON);
    }


}
