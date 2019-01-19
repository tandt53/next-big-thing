package com.tandt.automation.example;

import com.tandt.automation.example.annotations.FindElement;
import com.tandt.automation.example.element.BaseElement;
import com.tandt.automation.example.element.BaseElementInvocationHandler;
import com.tandt.automation.example.element.LocatorType;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public interface ElementSupplier {

    default <T> void initElements(final T targetPage) {

        try {
            Class<?> objectClass = targetPage.getClass();
//            Map<String, String> listElements = new HashMap<>();
            for (Field field : objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(FindElement.class)) {
                    LocatorType type = getLocatorType(field);
                    String value = getLocatorValue(field);
                    String name = field.getName();

                    if (type != null && value != null) {

//                        Object object = Class.forName(field.getType().toString().replace("class ", ""))
//                                .getConstructor(LocatorType.class, String.class, String.class)
//                                .newInstance(type, value, name);
//
//                        listElements.put(getLocatorValue(field), (String) field.get(targetPage));
//
//                        if (object == null)
//                            throw new NullPointerException("Element " + name + " is not initialized");
//                        else
//                            field.set(targetPage, object);

                        Object baseElement = (BaseElement) Proxy.newProxyInstance(BaseElement.class.getClassLoader(),
                                new Class[]{BaseElement.class}, new BaseElementInvocationHandler(type, value, name));
                        field.set(targetPage, baseElement);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default String getLocatorValue(Field field) {
        return field.getAnnotation(FindElement.class).value();
    }

    default LocatorType getLocatorType(Field field) {
        return field.getAnnotation(FindElement.class).type();
    }

}
