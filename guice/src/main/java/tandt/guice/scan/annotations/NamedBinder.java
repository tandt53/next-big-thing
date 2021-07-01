package tandt.guice.scan.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * if value == empty, superClass and superInterface must not be Class.class
 * if value != empty,
 * - if superInterface == empty, superClass must not be Class.class, then bind annotated class with superClass
 * - if superInterface != empty, bind annotated class with supperInterface
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NamedBinder {

    String named();

    Class<?> bind();


}
