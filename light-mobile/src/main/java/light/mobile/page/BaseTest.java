package light.mobile.page;

import com.google.inject.Inject;

/**
 * The base class for all test cases.
 */
public class BaseTest {

    @Inject
    private PageFactory pageFactory;

    protected <TPage extends BasePage> TPage page(Class<TPage> page) {
       return pageFactory.create(page);
    }

}
