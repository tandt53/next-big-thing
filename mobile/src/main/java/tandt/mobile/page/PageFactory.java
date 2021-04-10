package tandt.mobile.page;

import tandt.common.exceptions.CommonException;
import tandt.dataprovider.exceptions.PropertiesException;

import java.net.MalformedURLException;

/**
 * Provides a method for creating instance of page.
 */
public interface PageFactory {

//    <TPage extends BasePage<TPage>> TPage create(Class<? extends TPage> contract);

//    <TPage extends BasePage<TPage>> TPage create(Class<? extends TPage> contract, AbstractModule module);

    void create() throws PropertiesException, CommonException, MalformedURLException;
}
