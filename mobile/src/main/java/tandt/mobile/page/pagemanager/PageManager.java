package tandt.mobile.page.pagemanager;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import tandt.mobile.capability.CapabilityService;
import tandt.mobile.drivermanager.MobileDriverManager;
import tandt.mobile.page.BasePage;

public class PageManager {

    @Inject
    @Named("platformName")
    private String platform;

    @Inject
    private CapabilityService service;

    @Inject
    private MobileDriverManager driverManager;

    private Injector injector;

    public <TBinder extends PageBinder> PageManager setBinder(TBinder binder){
        injector = Guice.createInjector(binder);
        return this;
    }

    public <TPage extends BasePage> TPage getPage(Class<TPage> page) {
        return (TPage) injector.getInstance((Key.get(page, Names.named(platform))));
    }
}
