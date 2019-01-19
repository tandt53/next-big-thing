package com.tandt.automation.example.element;

import com.tandt.automation.example.annotations.Clocking;
import com.tandt.automation.example.utils.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class BaseElementInvocationHandler implements InvocationHandler {
	// ******************************
	// Fields
	// ******************************
	private BaseElement baseElement;
	Log log = new Log(BaseElement.class);

	public BaseElementInvocationHandler(LocatorType type, String value, String name) {
		baseElement = new BaseElementImpl(type, value, name);
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

			log.info("Method " + method.getName() + " on element " + ((BaseElementImpl) baseElement).getName() + " executed in " + Duration.between(start, end).toMillis() + " ms.");
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return returnObj;
	}

}
