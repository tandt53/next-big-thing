package tandt.guice.scan;

import com.google.inject.AbstractModule;
import org.reflections.Reflections;
import tandt.guice.GuiceScanProperties;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Scan all classes that matches:
 */
public class PageObjectScanner extends AbstractModule {

    @Override
    public void configure() {

        try {
            GuiceScanProperties properties = new GuiceScanProperties();
            String platform = properties.getProperty("guice.scan.page.package");
            String packageName = properties.getProperty("guice.scan.mobile.platform");
            Set<Class<?>> packageReflections = new Reflections(packageName).getTypesAnnotatedWith(PageObject.class);
            List<Class<?>> cs = packageReflections.stream()
                    .filter(c -> c.getAnnotation(PageObject.class).platform().equalsIgnoreCase(platform))
                    .collect(Collectors.toList());
            for (Class<?> c : cs) {
                Class T = c.getSuperclass();
                Class t = c;
                bind(T).to(t);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to install module", e);
        }
    }

}