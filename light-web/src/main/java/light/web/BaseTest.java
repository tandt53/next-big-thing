package light.web;

import com.google.inject.Inject;

/**
 * The base class for all test cases.
 */
public class BaseTest {

    @Inject
    private PageFactory pageFactory;

    protected <TPage extends BaseWebPage<TPage>> TPage page(Class<? extends TPage> contract) {
        return pageFactory.create(contract);
    }
}
