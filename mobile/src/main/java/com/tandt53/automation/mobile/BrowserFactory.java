package com.tandt53.automation.mobile;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.tandt53.automation.mobile.annotations.Android;
import com.tandt53.automation.mobile.annotations.Ios;
import com.tandt53.automation.mobile.drivermanager.Constants;
import com.tandt53.automation.mobile.drivermanager.MobileDriverBinder;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;


public class BrowserFactory {

    @Inject
    private static WebDriver driver;

    public static <T extends BaseTest<?>> void initPages(final T t) throws IllegalAccessException {
        Class<?> objectClass = t.getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            Injector injector = Guice.createInjector(new MobileDriverBinder());
            Object driver = null;
            if (field.isAnnotationPresent(Android.class)) {
                driver = injector.getInstance(Key.get(field.getType(), Names.named(Constants.DRIVER_TYPE_ANDROID)));
            } else if (field.isAnnotationPresent(Ios.class)) {
                driver = injector.getInstance(Key.get(field.getType(), Names.named(Constants.DRIVER_TYPE_IOS)));
            }
            field.set(t, driver);
        }
    }
}
