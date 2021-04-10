package tandt.mobile.test.pages;

import com.google.inject.name.Names;
import tandt.mobile.page.pagemanager.PageBinder;

public class HomePageBinder extends PageBinder<HomePageBinder> {
    @Override
    protected void configure() {
        bind(HomePage.class).annotatedWith(Names.named("android")).to(AndroidPage.class);
        bind(HomePage.class).annotatedWith(Names.named("ios")).to(IosPage.class);
    }
}
