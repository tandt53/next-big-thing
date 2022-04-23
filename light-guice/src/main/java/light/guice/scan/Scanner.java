package light.guice.scan;

import com.google.inject.AbstractModule;
import light.guice.exception.GuiceScannerException;
import light.guice.scan.model.BindingInfo;

import java.lang.annotation.Annotation;
import java.util.List;

public abstract class Scanner extends AbstractModule {

    protected abstract List<BindingInfo> builder();

    @Override
    protected void configure() {
        List<BindingInfo> bindingInfos = builder();
        if (bindingInfos != null || bindingInfos.size() > 0) {
            for (BindingInfo b : bindingInfos) {
                boolean isBindWithNamed = b.isBindWithAnnotation();
                Class superClass = b.getSuperClass();
                Class concreteClass = b.getConcreteClass();
                Annotation named = b.getNamed();

                if (concreteClass == null || concreteClass.equals(Class.class))
                    throw new GuiceScannerException("Concrete class must be defined or not equals to Class.class");

                if (superClass == null || superClass.equals(Class.class))
                    throw new GuiceScannerException("Super class must be defined or not equals to Class.class");

                if (isBindWithNamed) {
                    if (named == null) {
                        throw new GuiceScannerException("The value of named is not defined with concrete class" + concreteClass);
                    }
                    if (superClass.isAssignableFrom(concreteClass)) {
                        bind(superClass).annotatedWith(named).to(concreteClass);
                    } else
                        throw new GuiceScannerException("Super class " + superClass + " is not assignable from " + concreteClass);
                } else {
                    if (superClass.isAssignableFrom(concreteClass)) {
                        bind(superClass).to(concreteClass);
                    } else
                        throw new GuiceScannerException("Super class " + superClass + " is not assignable from " + concreteClass);
                }
            }
        }
    }
}
