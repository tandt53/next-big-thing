package light.mobile;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import light.mobile.drivermanager.AndroidDriverManager;
import light.mobile.drivermanager.DriverManager;
import light.mobile.drivermanager.DriverProvider;
import light.mobile.drivermanager.IosDriverManager;
import light.mobile.drivermanager.option.AppiumDriverOptionFilter;
import light.mobile.drivermanager.option.DriverOptionFilter;
import light.mobile.page.DefaultPageFactory;
import light.mobile.page.PageFactory;


@Singleton
public class MobileModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DriverOptionFilter.class).to(AppiumDriverOptionFilter.class);
        bind(PageFactory.class).to(DefaultPageFactory.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).toProvider(DriverProvider.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named("android")).to(AndroidDriverManager.class).in(Scopes.SINGLETON);
        bind(DriverManager.class).annotatedWith(Names.named("ios")).to(IosDriverManager.class).in(Scopes.SINGLETON);
    }


}
