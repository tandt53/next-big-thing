package com.tandt.automation.example.refactor.drivermanager;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.tandt.automation.example.refactor.drivermanager.DriverBinder;
import com.tandt.automation.example.refactor.drivermanager.DriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

public class DriverManagerFactory {

    public static WebDriver getManager(String driverType) {
        Injector injector = Guice.createInjector(new DriverBinder());
        return injector.getInstance((Key.get(DriverManager.class, Names.named(driverType)))).getDriver();
    }

    public WebDriver getManager(String driverType, DriverService service) {
        return null;
    }

    public WebDriver getManager(String driverType, MutableCapabilities caps) {
        return null;
    }

    public WebDriver getManager(String driverType, DriverService service, MutableCapabilities caps) {
        return null;
    }
}
