package onboarding.web.annotations;


import onboarding.ui.element.WaitStrategy;
import onboarding.web.element.WebLocatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FindElement {
    WebLocatorType type()  default WebLocatorType.ID;

    String value();

    WaitStrategy waitUntil()  default WaitStrategy.VISIBILITY;

}
