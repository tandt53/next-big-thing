package tandt.mobile.page.pagemanager;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import tandt.mobile.page.BasePage;

public class PageManager {

    @Inject
    @Named("platformName")
    private String platform;

    @Inject
    private Injector injector;

    public <TPage extends BasePage, TBinder extends PageBinder> TPage getPage(Class<TPage> page, TBinder binder) {
        Injector childInjector =injector.createChildInjector(binder);
        return (TPage) childInjector.getInstance((Key.get(page, Names.named(platform))));
    }
}
