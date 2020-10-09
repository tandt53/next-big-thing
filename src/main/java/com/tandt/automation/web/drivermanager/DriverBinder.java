package com.tandt.automation.web.drivermanager;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.openqa.selenium.WebDriver;

public class DriverBinder extends AbstractModule {
    @Override
    protected void configure() {
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_CHROME)).to(ChromeDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_FIREFOX)).to(ChromeDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_SAFARI)).to(ChromeDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_EDGE)).to(ChromeDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_REMOTE)).to(RemoteDriverManager.class);
    }

    @Provides
    @Named("chrome")
    public WebDriver getName(ChromeDriverManager object) {
        return object.getDriver();
    }

    @Provides
    @Named("firefox")
    public WebDriver getName(FirefoxDriverManager object) {
        return object.getDriver();
    }

    @Provides
    @Named("chrome")
    public DriverManager getManager() {
        return new ChromeDriverManager();
    }


}
