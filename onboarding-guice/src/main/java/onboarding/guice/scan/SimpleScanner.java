package onboarding.guice.scan;

import onboarding.commontest.TestContext;
import onboarding.commontest.configuration.Configuration;
import onboarding.guice.exception.GuiceScannerException;
import onboarding.guice.scan.annotations.SimpleBinder;
import onboarding.guice.scan.model.BindingInfo;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleScanner extends Scanner {
    private final String KEY_SIMPLE_VALUE = "onboarding.guice.scan.binder.simple.value";
    private final String KEY_SIMPLE_PACKAGE = "onboarding.guice.scan.binder.simple.package";
    private String value;
    private String packageName;
    private Set<Class<?>> cs;

    @Override
    protected List<BindingInfo> builder() {
        Configuration configuration = TestContext.getInstance().getConfiguration();
        value = (String) configuration.get(KEY_SIMPLE_VALUE);
        packageName = (String) configuration.get(KEY_SIMPLE_PACKAGE);

        if (value == null || value.isEmpty())
            throw new GuiceScannerException("The value of " + KEY_SIMPLE_VALUE + " is not defined.");

        if (packageName == null || packageName.isEmpty())
            throw new GuiceScannerException("The value of " + KEY_SIMPLE_PACKAGE + " is not defined.");

        Set<Class<?>> packageReflections = new Reflections(packageName)
                .getTypesAnnotatedWith(SimpleBinder.class);
        cs = filter(packageReflections);

        return bind();

    }

    public List<BindingInfo> bind() {
        List<BindingInfo> bindingInfos = new ArrayList<>();

        List<String> values = Arrays.asList(value.replaceAll("\\s", "").split(","));

        for (Class<?> c : cs) {
            Annotation annotation = c.getAnnotation(SimpleBinder.class);

            String v = ((SimpleBinder) annotation).value();
            Class bindClass = ((SimpleBinder) annotation).bind();

            if (v.isEmpty() && bindClass.equals(Class.class)) {
                bindingInfos.add(new BindingInfo(c.getSuperclass(), c));
            }

            if (v.isEmpty() && !bindClass.equals(Class.class)) {
                if (!c.isAssignableFrom(bindClass))
                    throw new GuiceScannerException("Bind class must be a super class or interface of " + c);
                bindingInfos.add(new BindingInfo(bindClass, c));
            }

            if (!v.isEmpty() && values.contains(v)) {
                if (!bindClass.equals(Class.class)) {
                    if (!bindClass.isAssignableFrom(c))
                        throw new GuiceScannerException("Bind class must be a super class or interface of " + c);
                    bindingInfos.add(new BindingInfo(bindClass, c));
                } else
                    bindingInfos.add(new BindingInfo(c.getSuperclass(), c));
            } else {
                throw new GuiceScannerException("The attribute " + v + " in annotation SimpleBinder of class " + c + " is not contained in " + KEY_SIMPLE_VALUE);
            }

        }
        return bindingInfos;
    }

    private Set<Class<?>> filter(Set<Class<?>> packageReflections) {
        return packageReflections.stream()
                .filter(c -> {
                    String getNamed = c.getAnnotation(SimpleBinder.class).value();
                    if (value == null || value.isEmpty())
                        return true;
                    else {
                        return value.contains(getNamed);
                    }
                })
                .collect(Collectors.toSet());
    }

}
