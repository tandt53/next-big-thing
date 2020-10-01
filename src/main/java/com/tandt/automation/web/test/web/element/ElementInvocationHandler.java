package com.tandt.automation.web.test.web.element;

import com.tandt.automation.web.test.web.element.model.ElementInfo;
import com.tandt.automation.web.test.web.annotations.Clocking;
import com.tandt.automation.web.test.web.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public ElementInvocationHandler(ElementInfo elementInfo, WebDriver driver, WebDriverWait wait) {
        baseElement = new ElementImpl(elementInfo, driver, wait);
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
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
//			e.printStackTrace();
        }
        return returnObj;
    }
}
