package com.tandt53.web.drivermanager;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class DriverBinder extends AbstractModule {
    @Override
    protected void configure() {
        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_CHROME)).to(ChromeDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_FIREFOX)).to(FirefoxDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_SAFARI)).to(SafariDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_EDGE)).to(ChromeDriverManager.class);


        bind(DriverManager.class).annotatedWith(Names.named(Constants.ENV_LOCAL + Constants.DOT + Constants.DRIVER_TYPE_CHROME)).to(ChromeDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.ENV_LOCAL + Constants.DOT + Constants.DRIVER_TYPE_FIREFOX)).to(FirefoxDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.ENV_LOCAL + Constants.DOT + Constants.DRIVER_TYPE_SAFARI)).to(SafariDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.ENV_LOCAL + Constants.DOT + Constants.DRIVER_TYPE_EDGE)).to(ChromeDriverManager.class);

        bind(DriverManager.class).annotatedWith(Names.named(Constants.ENV_REMOTE + Constants.DOT + Constants.DRIVER_TYPE_CHROME)).to(RemoteDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.ENV_REMOTE + Constants.DOT + Constants.DRIVER_TYPE_FIREFOX)).to(RemoteDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.ENV_REMOTE + Constants.DOT + Constants.DRIVER_TYPE_SAFARI)).to(RemoteDriverManager.class);
        bind(DriverManager.class).annotatedWith(Names.named(Constants.ENV_REMOTE + Constants.DOT + Constants.DRIVER_TYPE_EDGE)).to(RemoteDriverManager.class);

    }

}
