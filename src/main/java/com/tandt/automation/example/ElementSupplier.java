package com.tandt.automation.example;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.tandt.automation.example.annotations.HtmlElement;
import com.tandt.automation.example.element.LocatorType;

public interface ElementSupplier {

	default <T> void initElements(final T targetPage) {

		try {
			Class<?> objectClass = targetPage.getClass();
			Map<String, String> listElements = new HashMap<>();
			for (Field field : objectClass.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(HtmlElement.class)) {
					LocatorType type = getLocatorType(field);
					String value = getLocatortValue(field);

					if (type != null && value != null) {
						Object object = Class.forName(field.getType().toString().replace("class ", ""))
								.getConstructor(LocatorType.class, String.class).newInstance(type, value);
						listElements.put(getLocatortValue(field), (String) field.get(targetPage));
						field.set(targetPage, object);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	default String getLocatortValue(Field field) {
		return field.getAnnotation(HtmlElement.class).value();
	}

	default LocatorType getLocatorType(Field field) {
		return field.getAnnotation(HtmlElement.class).type();
	}

}
