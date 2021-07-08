package tandt.web;

import org.openqa.selenium.WebDriver;
import tandt.web.annotations.FindElement;
import tandt.web.element.ElementInvocationHandler;
import tandt.web.element.WebElementInfo;
import tandt.web.element.WebLocatorType;
import ui.element.Element;
import ui.element.WaitStrategy;
import ui.exception.ElementActionException;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class ElementFactory {

    public static <T extends BaseWebPage<?>> void initElements(WebDriver driver, T page) {
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

                        Element baseElement = (Element) Proxy.newProxyInstance(Element.class.getClassLoader(),
                                new Class[]{Element.class}, new ElementInvocationHandler(driver, webElementInfo));
                        field.set(page, baseElement);
                    }
                }
            }
        } catch (Exception e) {
            throw new ElementActionException("Element does not work as expected.", e);
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
