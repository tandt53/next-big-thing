package com.tandt53.automation.web.annotations;


import com.tandt53.automation.web.element.LocatorType;
import com.tandt53.automation.web.element.WaitStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.tandt53.automation.web.element.LocatorType.ID;
import static com.tandt53.automation.web.element.WaitStrategy.VISIBILITY;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface FindElement {
    LocatorType type()  default ID;

    String value();

    WaitStrategy waitUntil()  default VISIBILITY;

}
