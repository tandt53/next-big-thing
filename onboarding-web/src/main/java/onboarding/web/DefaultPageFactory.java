package onboarding.web;

import com.google.inject.Inject;
import com.google.inject.Injector;
import onboarding.web.page.BasePage;

public class DefaultPageFactory implements PageFactory {

    private Injector injector;

    @Inject
    public DefaultPageFactory(Injector injector) {
        this.injector = injector;
    }

    @Override
    public <TPage extends BasePage<TPage>> TPage create(Class<? extends TPage> contract) {
        return injector.getInstance(contract);
    }
}
