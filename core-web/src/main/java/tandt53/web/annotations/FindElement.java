package tandt53.web.annotations;


import tandt53.web.element.LocatorType;
import tandt53.web.element.WaitStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static tandt53.web.element.LocatorType.ID;
import static tandt53.web.element.WaitStrategy.VISIBILITY;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface FindElement {
    LocatorType type()  default ID;

    String value();

    WaitStrategy waitUntil()  default VISIBILITY;

}
