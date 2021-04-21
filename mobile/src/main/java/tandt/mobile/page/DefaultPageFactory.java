package tandt.mobile.page;

import com.google.inject.Inject;
import tandt.mobile.page.pagemanager.PageBinder;
import tandt.mobile.page.pagemanager.PageManager;

public class DefaultPageFactory implements PageFactory {

    protected PageManager pageManager;

    @Inject
    public DefaultPageFactory(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @Override
    public <TPage extends BasePage, TBinder extends PageBinder> TPage create(Class<TPage> page, TBinder pageBinder) {
        return pageManager.getPage(page, pageBinder);
    }
}
