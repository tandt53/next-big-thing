package tandt.web;

import com.google.inject.Inject;
import com.google.inject.Injector;
import tandt.web.drivermanager.DriverManagerFactory;

public class DefaultPageFactory implements PageFactory {

    private DriverManagerFactory driverManagerFactory;
    private Injector injector;

    @Inject
    public DefaultPageFactory(DriverManagerFactory driverManagerFactory, Injector injector) {
        this.driverManagerFactory = driverManagerFactory;
        this.injector = injector;
    }

    @Override
    public <TPage extends BaseWebPage<TPage>> TPage create(Class<? extends TPage> contract) {
        driverManagerFactory.getDriverManager();
        return injector.getInstance(contract);
    }
}
