package tandt.guice.test.abstracts;

import tandt.guice.scan.annotations.SimpleBinder;

@SimpleBinder(value = "ios")
public class IosPage extends BasePage{
    @Override
    public String login() {
        return "ios page";
    }
}
