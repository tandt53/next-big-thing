package tandt.guice;

import com.google.inject.Module;
import tandt.common.Log;
import tandt.guice.exception.GuiceLoaderException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GuiceModuleLoader {
    private final String GUICE_MODULES_PROPERTIES = "guice-modules.properties";
    private Log log = new Log(GuiceModuleLoader.class);

    /**
     * Load guice modules from properties file
     */
    public List<Module> loadModules() {
        Properties gProps = new Properties();
        try (InputStream ip = getClass().getClassLoader().getResourceAsStream(GUICE_MODULES_PROPERTIES)) {
            if (ip != null) {
                gProps.load(ip);
            }
        } catch (IOException e) {
            log.debug("Unable to find file guice-modules.properties in resources.");
        }
        Set<String> keys = gProps.stringPropertyNames();
        List<Module> modules = new ArrayList<>();
        String className = null;
        try {
            if (keys != null) {
                Iterator<String> it = keys.iterator();
                while (it.hasNext()) {
                    className = gProps.getProperty(it.next());
                    Module m = (Module) Class.forName(className).getDeclaredConstructor().newInstance();
                    modules.add(m);
                }
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            throw new GuiceLoaderException("Unable to load class " + className, e);
        }
        return modules;
    }
}