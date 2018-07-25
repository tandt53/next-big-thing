package com.tandt.automation.example.driver.provider;

import com.google.inject.AbstractModule;
import com.tandt.automation.example.driver.BaseWebDriver;

/**
 * Created by thetan.do on 12/24/2016.
 */
public class WebDriverInjector extends AbstractModule {

    protected void configure() {
        bind(BaseWebDriver.class).toProvider(WebDriverSelectorProvider.class);
    }
}
