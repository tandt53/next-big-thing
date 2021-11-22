package tandt.web.drivermanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import tandt.common.configurations.Prop;

public class WebDriverProvider implements Provider<DriverManager> {

    @Inject
    private Injector injector;

    @Inject
    @Prop("browser")
    private String browser;

    @Override
    public DriverManager get() {
        return injector.getInstance(Key.get(DriverManager.class, Names.named(browser)));
    }
}
