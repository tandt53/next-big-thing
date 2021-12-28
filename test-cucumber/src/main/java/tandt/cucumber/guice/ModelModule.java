package tandt.cucumber.guice;

import com.google.inject.AbstractModule;
import io.cucumber.guice.ScenarioScoped;
import tandt.cucumber.exceptions.ScenarioScopeModelException;
import tandt.guice.GuiceScanPropertiesLoader;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * ModelModule helps to manage the model objects in Cucumber scenario scope.
 * The subclass must implement the method getModelObjects() to return the list of model classes.
 */
public class ModelModule extends AbstractModule {

    private final String KEY = "cucumber.guice.scenario.scope.model";

    @Override
    protected void configure() {
        GuiceScanPropertiesLoader scanner = new GuiceScanPropertiesLoader();
        String className = scanner.getProperty(KEY);
        if (className != null) {
            try {
                Class<?> clazz = Class.forName(className);
                List<Class<?>> modelObjects = (List<Class<?>>) clazz.getMethod("getModels").invoke(null);
                for (Class<?> modelObject : modelObjects) {
                    bind(modelObject).in(ScenarioScoped.class);
                }
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                throw new ScenarioScopeModelException(e);
            }
        }
    }
}
