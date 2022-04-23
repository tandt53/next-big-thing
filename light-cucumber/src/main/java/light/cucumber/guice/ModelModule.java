package light.cucumber.guice;

import com.google.inject.AbstractModule;
import io.cucumber.guice.ScenarioScoped;
import light.commontest.TestContext;
import light.commontest.configuration.Configuration;
import light.cucumber.exceptions.ScenarioScopeModelException;

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
        Configuration configuration = TestContext.getInstance().getConfiguration();
        String className = (String) configuration.get(KEY);
        if (className != null) {
            try {
                Class<?> clazz = Class.forName(className);
                List<Class<?>> modelObjects = (List<Class<?>>) clazz.getMethod("getModels").invoke(clazz.getDeclaredConstructor().newInstance());
                for (Class<?> modelObject : modelObjects) {
                    bind(modelObject).in(ScenarioScoped.class);
                }
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
                throw new ScenarioScopeModelException(e);
            }
        }
    }
}
