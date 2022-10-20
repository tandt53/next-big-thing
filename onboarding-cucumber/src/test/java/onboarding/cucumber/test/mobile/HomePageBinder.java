package onboarding.cucumber.test.mobile;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import onboarding.cucumber.test.mobile.components.AndroidComponent;
import onboarding.cucumber.test.mobile.components.HomeComponent;
import onboarding.cucumber.test.mobile.components.IosComponent;

public class HomePageBinder extends AbstractModule {
    @Override
    protected void configure() {
        bind(HomeComponent.class).annotatedWith(Names.named("android")).to(AndroidComponent.class);
        bind(HomeComponent.class).annotatedWith(Names.named("ios")).to(IosComponent.class);
    }
}
