package tandt.web.annotations;


import tandt.web.element.LocatorType;
import tandt.web.element.WaitStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static tandt.web.element.LocatorType.ID;
import static tandt.web.element.WaitStrategy.VISIBILITY;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface FindElement {
    LocatorType type()  default ID;

    String value();

    WaitStrategy waitUntil()  default VISIBILITY;

}
