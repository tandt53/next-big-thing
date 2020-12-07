package com.tandt53.automation.mobile.element;

import com.tandt53.automation.common.Log;
import com.tandt53.automation.mobile.annotations.Clocking;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class ElementInvocationHandler implements InvocationHandler {
    // ******************************
    // Fields
    // ******************************
    private MobileElement baseMobileElement;
    Log log = new Log(MobileElement.class);

    public ElementInvocationHandler(ElementInfo elementInfo, AppiumDriver<WebElement> driver) {
        baseMobileElement = new MobileElementImpl(elementInfo, driver);
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
                return method.invoke(baseMobileElement, args);
            }

            // ... otherwise log the execution time of it.
            Instant start = Instant.now();
            returnObj = method.invoke(baseMobileElement, args);
            Instant end = Instant.now();

            log.info("Method " + method.getName() + " on element " + ((MobileElementImpl) baseMobileElement).getElementInfo().getName()
                    + " executed in " + Duration.between(start, end).toMillis() + " ms.");
            System.out.println("Method " + method.getName() + " on element " + ((MobileElementImpl) baseMobileElement).getElementInfo().getName()
                    + " executed in " + Duration.between(start, end).toMillis() + " ms.");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
        }
        return returnObj;
    }
}
