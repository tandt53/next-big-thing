package onboarding.web;

import com.google.inject.Inject;
import onboarding.web.page.BasePage;

/**
 * The base class for all test cases.
 */
public class BaseTest {

    @Inject
    private PageFactory pageFactory;

    protected <TPage extends BasePage<TPage>> TPage page(Class<? extends TPage> contract) {
        return pageFactory.create(contract);
    }
}
