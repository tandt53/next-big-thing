package tandt.web.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import tandt.common.exceptions.CommonException;
import tandt.web.capability.ICapabilityManagerService;
import tandt.web.modules.CoreModule;

import java.net.MalformedURLException;

public class Main {
    public static Injector injector;

    public static void main(String[] args) throws MalformedURLException, CommonException {
        injector = Guice.createInjector(new CoreModule());
        ICapabilityManagerService service = injector.getInstance(ICapabilityManagerService.class);
        service.loadCapabilities();

        String browser = service.getCapability().getValue("browser");
        String env = service.getCapability().getValue("env");

//        DriverManager driverManager = injector.getInstance(Key.get(DriverManager.class, Names.named(browser + "." + env)));
//        driverManager.initDriver();

        IDriverInit mainPage = injector.getInstance(IDriverInit.class);
        mainPage.initDriver();

//        TestDriver testDriver = new TestDriver();
//        testDriver.checkDriver();

    }
}
