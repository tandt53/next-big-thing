package tandt.guice.scan;

import com.google.common.collect.Sets;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import org.reflections.Reflections;
import tandt.guice.GuiceScanProperties;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModuleScanner extends AbstractModule {


    private final Set<Class<? extends Annotation>> installAnnotations;

    public ModuleScanner() {
        this(Binder.class);
    }

    @SafeVarargs
    public ModuleScanner(final Class<? extends Annotation>... installAnnotations) {
        super();
        this.installAnnotations = Sets.newHashSet(installAnnotations);
    }

    @Override
    public void configure() {

        try {
            GuiceScanProperties properties = new GuiceScanProperties();
            String packageName = properties.getProperty("guice.scan.module.package");

            Reflections packageReflections = new Reflections(packageName);
            List<Class<?>> cs = installAnnotations.stream()
                    .map(packageReflections::getTypesAnnotatedWith)
                    .flatMap(Set::stream)
                    .filter(Module.class::isAssignableFrom)
                    .collect(Collectors.toList());

            for (Class<?> c : cs) {
                this.install((Module) c.getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to install module", e);
        }
    }

}