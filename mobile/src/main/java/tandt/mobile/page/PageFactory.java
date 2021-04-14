package tandt.mobile.page;

import tandt.mobile.page.pagemanager.PageBinder;

/**
 * Provides a method for creating instance of page.
 */
public interface PageFactory {

//    void create();

    <TPage extends BasePage , TBinder extends PageBinder> TPage create(Class<TPage> page, TBinder pageBinder);
}
