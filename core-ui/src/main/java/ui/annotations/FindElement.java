package ui.annotations;


import ui.element.LocatorType;
import ui.element.WaitStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ui.element.LocatorType.ID;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FindElement {
    LocatorType type()  default ID;

    String value();

    WaitStrategy waitUntil()  default WaitStrategy.VISIBILITY;

}
