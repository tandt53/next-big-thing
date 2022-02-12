package tandt.web.drivermanager;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import tandt.commontest.Prop;
import tandt.web.exceptions.SeleniumDriverConfigException;

public class WebDriverProvider implements Provider<DriverManager> {

    @Inject
    private Injector injector;

    @Inject
    @Prop("nbt.selenium.browserName")
    private String browser;

    @Inject
    @Prop("nbt.selenium.server")
    private String server;

    @Override
    public DriverManager get() {
        return injector.getInstance(Key.get(DriverManager.class, Names.named(getNamed())));
    }

    private String getNamed() {
        if (server.equals("")) {
            throw new SeleniumDriverConfigException("No server specified");
        }
        if (browser.equals("")) {
            throw new SeleniumDriverConfigException("No browser specified");
        }

        return server.split("\\.")[0] + "." + browser.toLowerCase();

    }
}
