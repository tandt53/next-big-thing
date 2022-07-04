package onboarding.guice.test.abstracts;

import onboarding.guice.scan.annotations.SimpleBinder;

@SimpleBinder(value = "ios")
public class IosPage extends BasePage{
    @Override
    public String login() {
        return "ios page";
    }
}
