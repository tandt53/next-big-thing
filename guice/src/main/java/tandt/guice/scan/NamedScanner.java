package tandt.guice.scan;

import com.google.inject.name.Names;
import org.reflections.Reflections;
import tandt.guice.GuiceScanProperties;
import tandt.guice.exception.GuiceScannerException;
import tandt.guice.scan.annotations.NamedBinder;
import tandt.guice.scan.model.BindingInfo;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Scanner for {@link NamedBinder}
 */
public class NamedScanner extends Scanner {
    private final String KEY_NAMED_BINDER_PACKAGE = "guice.scan.binder.named.package";

    private Set<Class<?>> cs;
    private String packageName;
    private final Class annotation = NamedBinder.class;

    @Override
    protected List<BindingInfo> builder() {
        GuiceScanProperties properties = new GuiceScanProperties();
        packageName = properties.getProperty(KEY_NAMED_BINDER_PACKAGE);

        if (packageName == null || packageName.isEmpty())
            throw new GuiceScannerException("package name is not defined.");

        cs = new Reflections(packageName).getTypesAnnotatedWith(annotation);

        List<BindingInfo> bindingInfos = new ArrayList<>();

        for (Class c : cs) {
            Annotation anno = c.getAnnotation(annotation);
            String named = ((NamedBinder) anno).named();
            Class superClass = ((NamedBinder) anno).bind();
            if (named.isEmpty()) {
                throw new GuiceScannerException("Require named attribute in BasedBinder annotation of class " + c);
            } else {
                if (superClass.isAssignableFrom(c)) {
                    BindingInfo bindingInfo = new BindingInfo();
                    bindingInfo.setNamed(Names.named(named));
                    bindingInfo.setConcreteClass(c);
                    bindingInfo.setSuperClass(superClass);
                    bindingInfo.setBindWithAnnotation(true);

                    bindingInfos.add(bindingInfo);
                } else
                    throw new GuiceScannerException("Attribute superClass is incorrect or not be defined in NamedBinder annotation of class " + c);
            }
        }
        return bindingInfos;
    }


}
