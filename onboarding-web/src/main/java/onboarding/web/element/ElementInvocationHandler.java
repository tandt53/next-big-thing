package onboarding.web.element;

import onboarding.common.Log;
import onboarding.ui.annotations.Clocking;
import onboarding.ui.element.Element;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class ElementInvocationHandler implements InvocationHandler {
    private Element baseWebElement;
    Log log = new Log(ElementInvocationHandler.class);

    public ElementInvocationHandler(WebDriver driver, WebElementInfo webElementInfo) {
        baseWebElement = new BaseWebElementImpl(driver, webElementInfo);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        Object returnObj = null;
        try {
            // If the annotation is not present, just redirect the method call to its origin...
            if (!method.isAnnotationPresent(Clocking.class)) {
                return method.invoke(baseWebElement, args);
            }
            // ... otherwise log the execution time of it.
            Instant start = Instant.now();
            returnObj = method.invoke(baseWebElement, args);
            Instant end = Instant.now();

            log.info("Method " + method.getName() + " on element " + ((BaseWebElementImpl) baseWebElement).getElementInfo().getName()
                    + " executed in " + Duration.between(start, end).toMillis() + " ms.");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.getCause().printStackTrace();
        }
        return returnObj;
    }
}
