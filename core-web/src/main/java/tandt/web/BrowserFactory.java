package tandt.web;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import tandt.web.drivermanager.Constants;
import tandt.web.annotations.Chrome;
import tandt.web.annotations.FireFox;
import tandt.web.annotations.Safari;
import tandt.web.drivermanager.DriverBinder;
import tandt.web.drivermanager.CapabilityManager;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;

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
                driver = injector.getInstance(Key.get(field.getType(), Names.named(named + Constants.DOT + Constants.DRIVER_TYPE_CHROME)));
            } else if (field.isAnnotationPresent(FireFox.class)) {
                driver = injector.getInstance(Key.get(field.getType(), Names.named(named + Constants.DOT + Constants.DRIVER_TYPE_FIREFOX)));
            } else if (field.isAnnotationPresent(Safari.class)) {
                driver = injector.getInstance(Key.get(field.getType(), Names.named(named + Constants.DOT + Constants.DRIVER_TYPE_SAFARI)));
            }
            field.set(t, driver);
        }
    }
}
