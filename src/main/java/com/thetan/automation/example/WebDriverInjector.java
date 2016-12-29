package com.thetan.automation.example;

import com.google.inject.AbstractModule;
import com.thetan.automation.example.driver.AbstractWebDriver;

/**
 * Created by thetan.do on 12/24/2016.
 */
public class WebDriverInjector extends AbstractModule {

    protected void configure() {
        bind(AbstractWebDriver.class).toProvider(WebDriverSelectorProvider.class);
    }
}
