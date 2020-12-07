package com.tandt53.automation.mobile.drivermanager;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import static com.tandt53.automation.mobile.drivermanager.Constants.*;

public class MobileDriverBinder extends AbstractModule {
    @Override
    protected void configure() {
        bind(MobileDriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_ANDROID)).to(AndroidDriverManager.class);
        bind(MobileDriverManager.class).annotatedWith(Names.named(DRIVER_TYPE_IOS)).to(IosDriverManager.class);
    }

}
