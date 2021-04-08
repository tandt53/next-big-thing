package tandt.web.drivermanager;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import org.openqa.selenium.WebDriver;
import tandt.web.capability.Capability;
import tandt.web.capability.CapabilityManagerService;
import tandt.web.capability.ICapability;
import tandt.web.capability.ICapabilityManagerService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Singleton
public class DriverBinder extends AbstractModule {


    @Override
    protected void configure() {
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_CHROME)).to(ChromeDriverManager.class);
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_FIREFOX)).to(FirefoxDriverManager.class);
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_SAFARI)).to(SafariDriverManager.class);
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_EDGE)).to(ChromeDriverManager.class);
//
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_CHROME_LOCAL)).to(ChromeDriverManager.class);
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_FIREFOX_LOCAL)).to(FirefoxDriverManager.class);
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_SAFARI_LOCAL)).to(SafariDriverManager.class);
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_EDGE_LOCAL)).to(EdgeDriverManager.class);
//
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_CHROME_REMOTE)).to(RemoteDriverManager.class);
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_FIREFOX_REMOTE)).to(RemoteDriverManager.class);
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_SAFARI_REMOTE)).to(RemoteDriverManager.class);
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_EDGE_REMOTE)).to(RemoteDriverManager.class);
//        bind(ICapability.class).to(Capability.class);
//        bind(ICapabilityManagerService.class).to(CapabilityManagerService.class);
    }

}
