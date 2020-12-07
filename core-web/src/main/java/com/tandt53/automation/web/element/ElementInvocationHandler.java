package com.tandt53.automation.web.element;

import com.tandt53.automation.common.Log;
import com.tandt53.automation.web.annotations.Clocking;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class ElementInvocationHandler implements InvocationHandler {
    // ******************************
    // Fields
    // ******************************
    private Element baseElement;
    Log log = new Log(Element.class);

    public ElementInvocationHandler(ElementInfo elementInfo, WebDriver driver) {
        baseElement = new ElementImpl(elementInfo, driver);
    }

    // ******************************
    // Public methods
    // ******************************
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        Object returnObj = null;
        try {
            // If the annotation is not present, just redirect the method call to its
            // origin...
            if (!method.isAnnotationPresent(Clocking.class)) {
                return method.invoke(baseElement, args);
            }

            // ... otherwise log the execution time of it.
            Instant start = Instant.now();
            returnObj = method.invoke(baseElement, args);
            Instant end = Instant.now();

            log.info("Method " + method.getName() + " on element " + ((ElementImpl) baseElement).getElementInfo().getName()
                    + " executed in " + Duration.between(start, end).toMillis() + " ms.");
            System.out.println("Method " + method.getName() + " on element " + ((ElementImpl) baseElement).getElementInfo().getName()
                    + " executed in " + Duration.between(start, end).toMillis() + " ms.");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
//			e.printStackTrace();
        }
        return returnObj;
    }
}
