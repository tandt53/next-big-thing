package tandt.mobile.page;

import tandt.common.exceptions.CommonException;
import tandt.dataprovider.exceptions.PropertiesException;

import java.net.MalformedURLException;

/**
 * Provides a method for creating instance of page.
 */
public interface PageFactory {

    void create() throws PropertiesException, CommonException, MalformedURLException;
}
