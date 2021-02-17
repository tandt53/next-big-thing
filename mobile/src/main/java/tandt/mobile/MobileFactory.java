package tandt.mobile;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import tandt.mobile.annotations.Android;
import tandt.mobile.annotations.Ios;
import tandt.mobile.drivermanager.Constants;
import tandt.mobile.drivermanager.MobileDriverBinder;

import java.lang.reflect.Field;


public class MobileFactory {


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
