package light.web;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class DefaultPageFactory implements PageFactory {

    private Injector injector;

    @Inject
    public DefaultPageFactory(Injector injector) {
        this.injector = injector;
    }

    @Override
    public <TPage extends BaseWebPage<TPage>> TPage create(Class<? extends TPage> contract) {
        return injector.getInstance(contract);
    }
}
