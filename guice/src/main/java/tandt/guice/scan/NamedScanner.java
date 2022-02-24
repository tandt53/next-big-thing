package tandt.guice.scan;

import com.google.inject.name.Names;
import org.reflections.Reflections;
import tandt.commontest.TestContext;
import tandt.commontest.configuration.Configuration;
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
    private final String KEY_NAMED_BINDER_PACKAGE = "nbt.guice.scan.binder.named.package";

    private final Class annotation = NamedBinder.class;

    @Override
    protected List<BindingInfo> builder() {
        Configuration configuration = TestContext.getInstance().getConfiguration();
        String packageName = (String) configuration.get(KEY_NAMED_BINDER_PACKAGE);

        if (packageName == null || packageName.isEmpty())
            throw new GuiceScannerException("package name is not defined.");

        Set<Class<?>> cs = new Reflections(packageName).getTypesAnnotatedWith(annotation);

        List<BindingInfo> bindingInfos = new ArrayList<>();

        for (Class<?> c : cs) {
            Annotation anno = c.getAnnotation(annotation);
            String named = ((NamedBinder) anno).name();
            Class<?> superClass = ((NamedBinder) anno).bind();
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
