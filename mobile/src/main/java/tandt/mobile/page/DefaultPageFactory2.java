//package tandt.mobile.page;
//
//import com.google.inject.AbstractModule;
//import com.google.inject.Inject;
//import com.google.inject.Injector;
//import tandt.mobile.drivermanager.MobileDriverManager;
//
//public class DefaultPageFactoryBak implements PageFactory {
//
//    private MobileDriverManager _driver;
//    private Injector _injector;
//
//    @Inject
//    public DefaultPageFactoryBak(MobileDriverManager driver, Injector injector) {
//        _driver = driver;
//        _injector = injector;
//    }
//
//
//    @Override
//    public <TPage extends BasePage<TPage>> TPage create(Class<? extends TPage> contract) {
//        TPage page = _injector.getInstance(contract);
//        page.setDriver(_driver.getDriver());
//        return page;
//    }
//
//
//}
