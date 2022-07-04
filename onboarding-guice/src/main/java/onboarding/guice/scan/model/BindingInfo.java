package onboarding.guice.scan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.annotation.Annotation;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BindingInfo {
    private Class<?> superClass;
    private Class<?> concreteClass;
    private Annotation named;
    private boolean isBindWithAnnotation = false;

    public BindingInfo(Class<?> superClass, Class<?> concreteClass) {
        this.superClass = superClass;
        this.concreteClass = concreteClass;
    }
}
