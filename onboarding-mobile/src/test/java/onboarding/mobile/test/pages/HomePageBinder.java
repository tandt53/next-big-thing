package onboarding.mobile.test.pages;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class HomePageBinder extends AbstractModule {
    @Override
    protected void configure() {
        bind(HomePage.class).annotatedWith(Names.named("android")).to(AndroidPage.class);
        bind(HomePage.class).annotatedWith(Names.named("ios")).to(IosPage.class);
    }
}
