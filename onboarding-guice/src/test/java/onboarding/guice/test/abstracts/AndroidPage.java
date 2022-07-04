package onboarding.guice.test.abstracts;

import onboarding.guice.scan.annotations.SimpleBinder;

@SimpleBinder(value = "android")
public class AndroidPage extends BasePage{

    @Override
    public String login() {
        return "android page";
    }
}
