package com.tandt.automation.web.test.web.drivermanager;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class DriverBinder extends AbstractModule {
    @Override
    protected void configure() {
        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_CHROME)).to(ChromeDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_FIREFOX)).to(ChromeDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_SAFARI)).to(ChromeDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_EDGE)).to(ChromeDriverManager.class);

    }
}
