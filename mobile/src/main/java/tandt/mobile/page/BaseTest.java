package tandt.mobile.page;

import com.google.inject.Inject;
import tandt.common.exceptions.CommonException;
import tandt.dataprovider.exceptions.PropertiesException;
import tandt.mobile.page.pagemanager.PageManager;

import java.net.MalformedURLException;

/**
 * The base class for all test cases.
 */
public class BaseTest {

    @Inject
    private PageFactory pageFactory;

    @Inject
    protected PageManager pageManager;

    protected void page() throws CommonException, PropertiesException, MalformedURLException {
        pageFactory.create();
    }

}
