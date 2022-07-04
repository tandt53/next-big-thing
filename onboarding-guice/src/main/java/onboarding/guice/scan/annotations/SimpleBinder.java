package onboarding.guice.scan.annotations;

import onboarding.guice.exception.GuiceScannerException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * String value() is included by the value of property guice.scan.builder.simple.value (separated by comma)
 * if value is not specified, throw {@link GuiceScannerException} that value is not defined
 * if value is specified, binding annotated class to support class in bind() with Key
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleBinder {

    String value() default "";

    Class<?> bind() default Class.class;

}
