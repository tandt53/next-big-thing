package tandt.web;

import com.google.inject.Inject;

/**
 * The base class for all test cases.
 */
public class BaseTest {

    @Inject
    private PageFactory _pageFactory;

    protected <TPage extends BaseWebPage<TPage>> TPage page(Class<? extends TPage> contract) {
        return _pageFactory.create(contract);
    }
}
