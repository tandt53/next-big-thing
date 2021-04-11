package tandt.web;

import tandt.web.annotations.FindElement;
import tandt.web.element.BaseWebElement;
import tandt.web.element.ElementInvocationHandler;
import tandt.web.element.WebElementInfo;
import tandt.web.element.WebLocatorType;
import ui.element.WaitStrategy;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class ElementFactory {

    public static <T extends BaseWebPage<?>> void initElements(final T page) {
        try {
            Class<?> objectClass = page.getClass();
            for (Field field : objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(FindElement.class)) {
                    WebLocatorType type = getLocatorType(field);
                    String value = getLocatorValue(field);
                    String name = field.getName();
                    WaitStrategy waitUntil = getWaitStrategy(field);
                    if (type != null && value != null) {
                        WebElementInfo webElementInfo = new WebElementInfo();
                        webElementInfo.setName(name);
                        webElementInfo.setLocatorType(type);
                        webElementInfo.setLocatorValue(value);
                        webElementInfo.setStrategy(waitUntil);

                        BaseWebElement baseElement = (BaseWebElement) Proxy.newProxyInstance(BaseWebElement.class.getClassLoader(),
                                new Class[]{BaseWebElement.class}, new ElementInvocationHandler(webElementInfo));
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

    private static WebLocatorType getLocatorType(Field field) {
        return field.getAnnotation(FindElement.class).type();
    }
}
