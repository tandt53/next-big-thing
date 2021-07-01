package tandt.guice.test.abstracts;

import tandt.guice.scan.annotations.SimpleBinder;

@SimpleBinder(value = "android")
public class AndroidPage extends BasePage{

    @Override
    public String login() {
        return "android page";
    }
}
