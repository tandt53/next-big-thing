package com.tandt53.automation.mobile.annotations;


import com.tandt53.automation.mobile.element.LocatorType;
import com.tandt53.automation.mobile.element.WaitStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.tandt53.automation.mobile.element.LocatorType.ID;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface FindElement {
    LocatorType type() default ID;

    String value();

    WaitStrategy waitUntil() default WaitStrategy.VISIBILITY;

}
