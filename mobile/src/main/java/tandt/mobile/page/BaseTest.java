package tandt.mobile.page;

import com.google.inject.Inject;
import tandt.mobile.page.pagemanager.PageBinder;

/**
 * The base class for all test cases.
 */
public class BaseTest {

    @Inject
    private PageFactory pageFactory;

//    @Inject
//    protected PageManager pageManager;

    protected <TPage extends BasePage, TBinder extends PageBinder> TPage page(Class<TPage> page, TBinder pageBinder) {
       return pageFactory.create(page, pageBinder);
    }

}
