package com.tandt53.automation.web;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.tandt53.automation.web.annotations.Chrome;
import com.tandt53.automation.web.annotations.FireFox;
import com.tandt53.automation.web.annotations.Safari;
import com.tandt53.automation.web.drivermanager.Constants;
import com.tandt53.automation.web.drivermanager.DriverBinder;
import com.tandt53.automation.web.drivermanager.CapabilityManager;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;

import static com.tandt53.automation.web.drivermanager.Constants.DOT;

public class BrowserFactory {

    @Inject
    private static WebDriver driver;

    public static <T extends BaseTest<?>> void initPages(final T t) throws IllegalAccessException {
        Class<?> objectClass = t.getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            Injector injector = Guice.createInjector(new DriverBinder());
            Object driver = null;
            String named = CapabilityManager.getEnv();
            if (field.isAnnotationPresent(Chrome.class)) {
                driver = injector.getInstance(Key.get(field.getType(), Names.named(named + DOT + Constants.DRIVER_TYPE_CHROME)));
            } else if (field.isAnnotationPresent(FireFox.class)) {
                driver = injector.getInstance(Key.get(field.getType(), Names.named(named + DOT + Constants.DRIVER_TYPE_FIREFOX)));
            } else if (field.isAnnotationPresent(Safari.class)) {
                driver = injector.getInstance(Key.get(field.getType(), Names.named(named + DOT + Constants.DRIVER_TYPE_SAFARI)));
            }
            field.set(t, driver);
        }
    }
}
