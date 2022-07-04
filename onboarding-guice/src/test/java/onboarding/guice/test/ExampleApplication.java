package onboarding.guice.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import onboarding.guice.scan.NamedScanner;
import onboarding.guice.scan.SimpleScanner;
import onboarding.guice.test.abstracts.BasePage;
import onboarding.guice.test.interfaces.Log;

public class ExampleApplication {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SimpleScanner(), new NamedScanner());

        BasePage page = injector.getInstance(BasePage.class);
        page.login();

        Log log = injector.getInstance(Key.get(Log.class, Names.named("console")));
        log.print();
    }
}
