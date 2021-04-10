package tandt.web.annotations;


import tandt.web.element.WebLocatorType;
import ui.element.WaitStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static tandt.web.element.WebLocatorType.ID;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface FindElement {
    WebLocatorType type()  default ID;

    String value();

    WaitStrategy waitUntil()  default WaitStrategy.VISIBILITY;

}
