package com.tandt53.mobile.annotations;


import com.tandt53.mobile.element.LocatorType;
import com.tandt53.mobile.element.WaitStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface FindElement {
    LocatorType type()  default LocatorType.ID;

    String value();

    WaitStrategy waitUntil()  default WaitStrategy.VISIBILITY;

}
