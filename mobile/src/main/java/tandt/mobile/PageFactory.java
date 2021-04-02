package tandt.mobile;

import tandt.mobile.annotations.FindElement;
import tandt.mobile.element.*;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class PageFactory {

    public static <T extends BaseMobilePage<?>> void initElements(final T page, AppiumDriver<WebElement> driver) {
        try {
            Class<?> objectClass = page.getClass();
            for (Field field : objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(FindElement.class)) {
                    LocatorType type = getLocatorType(field);
                    String value = getLocatorValue(field);
                    String name = field.getName();
                    WaitStrategy waitUntil = getWaitStrategy(field);
                    if (type != null && value != null) {
                        ElementInfo elementInfo = new ElementInfo();
                        elementInfo.setName(name);
                        elementInfo.setLocatorType(type);
                        elementInfo.setLocatorValue(value);
                        elementInfo.setStrategy(waitUntil);

                        Object baseElement = (BaseMobileElement) Proxy.newProxyInstance(BaseMobileElement.class.getClassLoader(),
                                new Class[]{BaseMobileElement.class}, new ElementInvocationHandler(elementInfo, driver));
                        field.set(page, baseElement);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static WaitStrategy getWaitStrategy(Field field) {
        return field.getAnnotation(FindElement.class).waitUntil();
    }

    private static String getLocatorValue(Field field) {
        return field.getAnnotation(FindElement.class).value();
    }

    private static LocatorType getLocatorType(Field field) {
        return field.getAnnotation(FindElement.class).type();
    }
}