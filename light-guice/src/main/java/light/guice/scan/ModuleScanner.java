package light.guice.scan;

import com.google.common.collect.Sets;
import com.google.inject.AbstractModule;
import light.guice.scan.annotations.Module;
import org.reflections.Reflections;
import light.commontest.TestContext;
import light.commontest.configuration.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModuleScanner extends AbstractModule {

    private final Set<Class<? extends Annotation>> installAnnotations;

    public ModuleScanner() {
        this(Module.class);
    }

    @SafeVarargs
    public ModuleScanner(final Class<? extends Annotation>... installAnnotations) {
        super();
        this.installAnnotations = Sets.newHashSet(installAnnotations);
    }

    @Override
    public void configure() {
        try {
            Configuration configuration = TestContext.getInstance().getConfiguration();
            String packageName = (String) configuration.get("light.guice.scan.module.package");
            if(packageName != null){
                Reflections packageReflections = new Reflections(packageName);
                List<Class<?>> cs = installAnnotations.stream()
                        .map(packageReflections::getTypesAnnotatedWith)
                        .flatMap(Set::stream)
                        .filter(com.google.inject.Module.class::isAssignableFrom)
                        .collect(Collectors.toList());

                for (Class<?> c : cs) {
                    this.install((com.google.inject.Module) c.getDeclaredConstructor().newInstance());
                }
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new IllegalStateException("Failed to install module", e);
        }
    }

}