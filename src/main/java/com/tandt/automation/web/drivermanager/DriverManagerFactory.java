package com.tandt.automation.web.drivermanager;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManagerFactory {

    private static ThreadLocal<WebDriver> manager = new ThreadLocal<>();

    /**
     * Init driver based on expected browser type: chrome, firefox, safari
     * @param driverType
     * @return
     */
    public static WebDriver getDriver(String driverType) {
        Injector injector = Guice.createInjector(new DriverBinder());
        WebDriver driver = injector.getInstance((Key.get(DriverManager.class, Names.named(driverType)))).getDriver();
        manager.set(driver);
        return manager.get();
    }

    /**
     *
     * @param driverType
     * @param service
     * @return
     */
    private WebDriver getDriver(String driverType, DriverService service) {
        return null;
    }

    /**
     * Init driver based on browser type, remote URL and capabilities/options
     * @param browser
     * @param remoteAddress
     * @param driverType
     * @param caps
     * @return
     * @throws MalformedURLException
     */
    public static WebDriver getDriver(String browser, String remoteAddress, String driverType, MutableCapabilities caps) throws MalformedURLException {
        Injector injector = Guice.createInjector(new DriverBinder());
        return injector.getInstance((Key.get(DriverManager.class, Names.named(driverType)))).getDriver(browser, new URL(remoteAddress), caps);
    }

    private WebDriver getDriver(String driverType, DriverService service, MutableCapabilities caps) {
        return null;
    }

    public static WebDriver getDriver() {
        return manager.get() != null ? manager.get() : getDriver(Constants.DRIVER_TYPE_CHROME);
    }
}
