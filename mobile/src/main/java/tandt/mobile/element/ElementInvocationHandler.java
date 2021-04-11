package tandt.mobile.element;

import tandt.common.Log;
import tandt.mobile.annotations.Clocking;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class ElementInvocationHandler implements InvocationHandler {
    // ******************************
    // Fields
    // ******************************
    private BaseMobileElement baseMobileElement;
    Log log = new Log(BaseMobileElement.class);

    public ElementInvocationHandler(MobileElementInfo mobileElementInfo) {
        baseMobileElement = new BaseMobileElementImpl(mobileElementInfo);
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

            log.info("Method " + method.getName() + " on element " + ((BaseMobileElementImpl) baseMobileElement).getElementInfo().getName()
                    + " executed in " + Duration.between(start, end).toMillis() + " ms.");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
        }
        return returnObj;
    }
}
