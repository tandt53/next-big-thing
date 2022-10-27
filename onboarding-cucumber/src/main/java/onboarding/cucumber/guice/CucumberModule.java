package onboarding.cucumber.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import onboarding.cucumber.steps.ElementManager;
import onboarding.cucumber.steps.MobileElementManager;
import onboarding.cucumber.steps.WebElementManager;

public class CucumberModule extends AbstractModule {

    @Override
    public void configure(){
        bind(ElementManager.class).annotatedWith(Names.named("mobile")).to(MobileElementManager.class);
        bind(ElementManager.class).annotatedWith(Names.named("web")).to(WebElementManager.class);
    }

}
