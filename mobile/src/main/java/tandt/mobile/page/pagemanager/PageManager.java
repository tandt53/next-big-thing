package tandt.mobile.page.pagemanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import tandt.commontest.Prop;
import tandt.mobile.page.BasePage;

@Singleton
public class PageManager {

    @Inject
    @Prop("platformName")
    private String platform;

    @Inject
    private Injector injector;

    public <TPage extends BasePage<?>> TPage getPage(Class<TPage> page) {
        return injector.getInstance((Key.get(page, Names.named(platform))));
    }
}
