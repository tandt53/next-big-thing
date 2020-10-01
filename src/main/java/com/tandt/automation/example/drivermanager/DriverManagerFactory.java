package com.tandt.automation.example.drivermanager;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

public class DriverManagerFactory {

    public static WebDriver getDriver(String driverType) {
        Injector injector = Guice.createInjector(new DriverBinder());
        return injector.getInstance((Key.get(DriverManager.class, Names.named(driverType)))).getDriver();
    }

    public WebDriver getDriver(String driverType, DriverService service) {
        return null;
    }

    public WebDriver getDriver(String driverType, MutableCapabilities caps) {
        return null;
    }

    public WebDriver getDriver(String driverType, DriverService service, MutableCapabilities caps) {
        return null;
    }
}
