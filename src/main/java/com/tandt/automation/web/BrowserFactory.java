package com.tandt.automation.web;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.tandt.automation.web.annotations.Browser;
import com.tandt.automation.web.drivermanager.DriverBinder;
import org.openqa.selenium.WebDriver;
import org.testng.util.Strings;

import java.lang.reflect.Field;

public class BrowserFactory {

    @Inject
    private static WebDriver driver;

    public static <T extends BaseTest<?>> void initPages(final T t) throws IllegalAccessException {
        Class<?> objectClass = t.getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Browser.class)) {
                String browser = field.getAnnotation(Browser.class).value();
                if(!Strings.isNullOrEmpty(browser)){
                    Injector injector = Guice.createInjector(new DriverBinder());

                    Object driver = injector.getInstance(Key.get(field.getType(), Names.named(browser)));
                    field.set(t, driver);
                }
            }
        }
    }
}
