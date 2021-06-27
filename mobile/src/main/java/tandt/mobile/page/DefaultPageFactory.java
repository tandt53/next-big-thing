package tandt.mobile.page;

import com.google.inject.Inject;
import tandt.mobile.page.pagemanager.PageManager;

public class DefaultPageFactory implements PageFactory {

    protected PageManager pageManager;

    @Inject
    public DefaultPageFactory(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @Override
    public <TPage extends BasePage> TPage create(Class<TPage> page) {
        return pageManager.getPage(page);
    }
}
